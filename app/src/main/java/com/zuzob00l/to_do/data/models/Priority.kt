package com.zuzob00l.to_do.data.models

import androidx.compose.ui.graphics.Color
import com.zuzob00l.to_do.ui.theme.HighPriorityColor
import com.zuzob00l.to_do.ui.theme.LowPriorityColor
import com.zuzob00l.to_do.ui.theme.MediumPriorityColor

enum class Priority(val color: Color)
{
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(Color.LightGray)
}