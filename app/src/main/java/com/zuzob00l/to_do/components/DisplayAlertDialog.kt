package com.zuzob00l.to_do.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zuzob00l.to_do.ui.theme.ListItemColor

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit)
{
    if(openDialog)
        AlertDialog(
            title = { Text(
                text = title,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold) },
            text = { Text(
                text = message,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Normal) },
            onDismissRequest = { closeDialog() },
            confirmButton = { Button(
                onClick = {
                    onYesClicked()
                    closeDialog()},
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.ListItemColor))
            {
                Text(text = "Yes")
            } },
            dismissButton = { OutlinedButton(
                onClick = { closeDialog() },
                border = BorderStroke(1.dp,color = Color.Gray)
            )
            {
               Text(
                   text = "No",
                   color = Color.Gray )
            } })
}
