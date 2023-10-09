package com.zuzob00l.to_do.viewModels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zuzob00l.to_do.data.models.ToDoTask
import com.zuzob00l.to_do.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel()
{

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks
    fun getAllTask() {
        viewModelScope.launch {
            repository.getAllTasks.collect {
                //collect value:
                _allTasks.value = it
            }
        }
    }
}