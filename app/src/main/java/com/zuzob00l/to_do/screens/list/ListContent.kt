package com.zuzob00l.to_do.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.data.models.ToDoTask
import com.zuzob00l.to_do.ui.theme.ListItemColor
import com.zuzob00l.to_do.ui.theme.ListItemTextColor
import com.zuzob00l.to_do.ui.theme.PRIORITY_INDICATOR_SIZE
import com.zuzob00l.to_do.util.RequestState
import com.zuzob00l.to_do.util.SearchAppBarState

@ExperimentalMaterialApi
@Composable
fun ListContent(
    modifier: Modifier,
    allTasks: RequestState<List<ToDoTask>>,
    searchAppBarState: SearchAppBarState,
    searchedTasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit)
{
   if(searchAppBarState == SearchAppBarState.TRIGGERED)
   {
       if(searchedTasks is RequestState.Success)
           HandleListContent(
               tasks = searchedTasks.data,
               navigateToTaskScreen = navigateToTaskScreen)
   }
    else
    {
       if (allTasks is RequestState.Success)
           HandleListContent(
               tasks = allTasks.data,
               navigateToTaskScreen = navigateToTaskScreen)
    }
}
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit)
{
    if(tasks.isEmpty())
        EmptyContent()
    else
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen)
}

@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
)
{
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
        items(
            items = tasks,
            key = { task -> task.id })
        {
                task -> TaskItem(
            toDoTask = task,
            navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit)
{
    Surface(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.ListItemColor,
        shape = RoundedCornerShape(10),
        elevation = 4.dp,
        onClick = { navigateToTaskScreen(toDoTask.id) })
    {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth())
        {
            Row()
            {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colors.ListItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd)
                {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
                        drawCircle(color = toDoTask.priority.color) }
                }
            }
            //Outside Row:
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.ListItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Preview()
@Composable
fun TaskItemPreview()
{
    var toDoTask = ToDoTask(
        id = 0,
        title = "Preview",
        description = "task for Preview",
        priority = Priority.LOW)

    TaskItem(toDoTask = toDoTask, navigateToTaskScreen = {})
}