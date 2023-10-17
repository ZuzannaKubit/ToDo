package com.zuzob00l.to_do.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.components.PriorityItem
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.ui.theme.BackgroundColor
import com.zuzob00l.to_do.ui.theme.LARGE_PADDING
import com.zuzob00l.to_do.ui.theme.Typography
import com.zuzob00l.to_do.ui.theme.topAppBarBackground
import com.zuzob00l.to_do.ui.theme.topAppBarColor
import com.zuzob00l.to_do.util.SearchAppBarState
import com.zuzob00l.to_do.util.TrailingIconState
import com.zuzob00l.to_do.viewModels.SharedViewModel

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String)
{
    when(searchAppBarState)
    {
        SearchAppBarState.CLOSED -> { DefaultListAppBar(
            onSearchClicked = { sharedViewModel.searchAppBarState.value = SearchAppBarState.OPEN },
            onSortClicked = {},
            onDeleteClicked = {}) }
          else -> {
              SearchAppBar(
            text = searchTextState,
            onTextChange = { newText ->
                sharedViewModel.searchTextState.value = newText },
            onSearchClicked = {},
            onCloseClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                sharedViewModel.searchTextState.value = ""}) }

    }
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit)
{
   TopAppBar(
       title = { Text(text = "Tasks", color = MaterialTheme.colors.topAppBarColor) },
       actions = { ListAppBarActions( onSearchClicked = onSearchClicked, onSortClicked, onDeleteClicked) },
       backgroundColor = MaterialTheme.colors.topAppBarBackground)
}
@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit)
{
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAll(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit)
{
    IconButton(onClick = { onSearchClicked() })
    {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_tasks),
            tint = Color.White)
    }
}
@Composable
fun SortAction(onSortClicked: (Priority) -> Unit)
{
    var expanded by remember { mutableStateOf(false) }

   IconButton(onClick = { expanded = true })
   {
       Icon(
           painter = painterResource(R.drawable.ic_filter_list),
           tint = Color.White,
           contentDescription = stringResource(R.string.sort_tasks))

       DropdownMenu(
           expanded = expanded,
           onDismissRequest = { expanded = false })
       {
   //LOW////////////////////////////////////////////////////////////////////////
           DropdownMenuItem(onClick = {
               expanded = false
               onSortClicked(Priority.LOW)})
           {
               PriorityItem(Priority.LOW)
           }
   //MEDIUM//////////////////////////////////////////////////////////////////////
           DropdownMenuItem(onClick = {
               expanded = false
               onSortClicked(Priority.MEDIUM)})
           {
               PriorityItem(Priority.MEDIUM)
           }
   //HIGH////////////////////////////////////////////////////////////////////////
           DropdownMenuItem(onClick = {
               expanded = false
               onSortClicked(Priority.HIGH)})
           {
               PriorityItem(Priority.HIGH)
           }
       }
   }
}

@Composable
fun DeleteAll(onDeleteClicked: () -> Unit)
{
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true })
    {
        Icon(
            painter = painterResource(R.drawable.ic_menu),
            tint = Color.White,
            contentDescription = stringResource(R.string.vertical_menu))

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false })
        {
            DropdownMenuItem(onClick = {
                expanded = false
                onDeleteClicked() })
            {
              Text(
                  modifier = Modifier.padding(start = LARGE_PADDING),
                  text = stringResource(R.string.delete_all_action),
                  fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit)
{
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = 4.dp,
        color = BackgroundColor)
    {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                text = "Search",
                color = Color.White,
                fontSize = 18.sp) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                imageVector = Icons.Filled.Search,
                tint = Color.White,
                contentDescription = stringResource(R.string.search_tasks)) },
            trailingIcon = {
                IconButton(onClick = {
                   when(trailingIconState) {
                       //we clicked first time on close button and remove actual text
                       TrailingIconState.READY_TO_DELETE -> {
                           onTextChange("")
                           trailingIconState = TrailingIconState.READY_TO_CLOSE }
                       //we clicked second time and close Search Bar:
                       TrailingIconState.READY_TO_CLOSE -> {
                           if(text.isNotEmpty())
                           onTextChange("")
                           else {
                               onCloseClicked()
                               trailingIconState = TrailingIconState.READY_TO_DELETE }

                       }
                   }
                     })
                {
                   Icon(
                       imageVector = Icons.Filled.Close,
                       tint = Color.White,
                       contentDescription = stringResource(R.string.close_icon))
                 }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) })
        )
        //end of TextField
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultListAppBarPreview()
{
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {})
}
@Composable
@Preview(showBackground = true)
fun SearchAppBarPreview()
{
    SearchAppBar(
        text = "search",
        onTextChange = {},
        onSearchClicked = { }) {

    }
}
