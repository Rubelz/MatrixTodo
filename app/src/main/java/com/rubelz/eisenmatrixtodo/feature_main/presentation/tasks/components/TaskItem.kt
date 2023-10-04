package com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.rubelz.eisenmatrixtodo.EisenMatrixApp
import com.rubelz.eisenmatrixtodo.feature_main.domain.models.TaskModel
import com.rubelz.eisenmatrixtodo.ui.theme.EisenMatrixTodoTheme

@Composable
fun TaskItem(
    task: TaskModel,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier,

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = Color(0x92FFFFFF))

        ) {
            Text(
                text = task.title,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,

                )

        }
    }
}

@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(TaskModel(null, "ueuentuhenu", "ueueu", -1), onDeleteClick = {})
}