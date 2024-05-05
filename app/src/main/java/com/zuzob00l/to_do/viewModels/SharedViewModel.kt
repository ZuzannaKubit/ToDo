package com.zuzob00l.to_do.viewModels

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.data.models.ToDoTask
import com.zuzob00l.to_do.repository.DataStoreRepository
import com.zuzob00l.to_do.repository.ToDoRepository
import com.zuzob00l.to_do.util.Actions
import com.zuzob00l.to_do.util.Constants.MAX_TITLE_LENGTH
import com.zuzob00l.to_do.util.RequestState
import com.zuzob00l.to_do.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository,
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    val action: MutableState<Actions> = mutableStateOf(Actions.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks

// values and function for Search in database///////////////////////////////////////////////////////////////////////
    private val _searchedTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val searchTask: StateFlow<RequestState<List<ToDoTask>>> = _searchedTask

    fun searchDatabase(searchQuery: String)
    {
        _searchedTask.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.searchDatabase(searchQuery = "%$searchQuery%")
                    .collect{  searchedTasks ->
                        _searchedTask.value = RequestState.Success(searchedTasks)
                    }
            }
        }
        catch (e: Exception)
        {
            _searchedTask.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }
//sorting by priority:///////////////////////////////////////////////////////////////////////////////
    private val _sortState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<Priority>> = _sortState

    fun readSortState()
    {
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                dataStoreRepository.readSortState
                    .map { Priority.valueOf(it) }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }
        }
        catch (e: Exception) {
            _sortState.value = RequestState.Error(e)
        }
    }
    fun persistSortState(priority: Priority)
    {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.persistSortState(priority)
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////

    fun getAllTask() {

        _allTasks.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {

                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)

    val selectedTask: StateFlow<ToDoTask?> = _selectedTask
    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect { task ->

                _selectedTask.value = task
            }
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO)
        {
          val toDoTask = ToDoTask(
              title = title.value,
              description = description.value,
              priority = priority.value)

            repository.addTask(toDoTask)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title  = title.value,
                description = description.value,
                priority = priority.value)

            repository.updateTask(toDoTask)
        }
    }

    private fun deleteTask()
    {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value)

            repository.deleteTask(toDoTask)
        }
    }

    private fun deleteAllTasks()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun handleDatabaseAction(action: Actions)
    {
        when(action){
            Actions.ADD -> { addTask() }
            Actions.UPDATE -> { updateTask() }
            Actions.DELETE -> { deleteTask() }
            Actions.DELETE_ALL -> { deleteAllTasks() }
            Actions.UNDO -> { addTask() }
            else -> {}
        }
        this.action.value = Actions.NO_ACTION
    }

    fun updateTaskField(selectedTask: ToDoTask?) {
        //task will be null when user clicked the Floating action button (FAB content button)
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {
        //limit for a title: 25 characters
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields() : Boolean
    {
        //return true if this conditions are true
        //if title and desc are empty return false
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}