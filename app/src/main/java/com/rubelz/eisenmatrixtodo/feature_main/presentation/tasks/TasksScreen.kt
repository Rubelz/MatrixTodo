package com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rubelz.eisenmatrixtodo.R
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components.TaskContainer
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components.TaskItem
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components.TaskType
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components.TaskTypeBanner
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components.TaskViewModel
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.components.TasksEvent
import com.rubelz.eisenmatrixtodo.feature_main.presentation.util.Screen
import com.rubelz.eisenmatrixtodo.ui.theme.inu
import com.rubelz.eisenmatrixtodo.ui.theme.iu
import com.rubelz.eisenmatrixtodo.ui.theme.ninu
import com.rubelz.eisenmatrixtodo.ui.theme.niu
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Box(modifier = Modifier) {

            GlideImage(
                model = R.drawable.winter_night,
                contentDescription = null,
                modifier = Modifier.blur(5.dp),
                contentScale = ContentScale.Crop,

            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Row(Modifier.weight(0.5f)) {
                    TaskContainer(
                        navController = navController,
                        taskType = TaskType(
                            taskType = 1,
                            stringResource(id = R.string.IU),
                            color = iu
                        ),
                        modifier = Modifier.weight(.5f)
                    )
                    Divider(
                        color = Color.Black, modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    TaskContainer(
                        navController = navController, taskType = TaskType(
                            taskType = 2, stringResource(id = R.string.INU), color = inu
                        ), modifier = Modifier.weight(.5f)
                    )
                }
                Divider(color = Color.Black, modifier = Modifier.fillMaxWidth())
                Row(Modifier.weight(0.5f)) {
                    TaskContainer(
                        navController = navController, taskType = TaskType(
                            taskType = 3, stringResource(id = R.string.NIU), color = niu
                        ), modifier = Modifier.weight(.5f)
                    )
                    Divider(
                        color = Color.Black, modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    TaskContainer(
                        navController = navController, taskType = TaskType(
                            taskType = 4, stringResource(id = R.string.NINU), color = ninu
                        ), modifier = Modifier.weight(.5f)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTaskBanner() {
    Surface {

        TasksScreen(navController = rememberNavController())

    }
}