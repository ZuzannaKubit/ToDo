package com.zuzob00l.to_do.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.ui.theme.Purple40

@Composable
fun StartScreen(
    navController: NavController,
    onStartClicked: () -> Unit = {})
{
    val backgroundImage = ImageBitmap.imageResource(id = R.drawable.ic_background)

    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = backgroundImage,
        contentDescription = "backgroundImage")

    Column(
        modifier = Modifier
            .padding(bottom = 100.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom)
    {
        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20)
        )
        {
            Text(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = "Let's plane something",
                 color = Purple40)
        }
    }

}
/*
@Preview()
@Composable()
fun StartScreenPreview() {
    StartScreen()
}
*/
