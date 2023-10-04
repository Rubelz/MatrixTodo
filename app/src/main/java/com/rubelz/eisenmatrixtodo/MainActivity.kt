package com.rubelz.eisenmatrixtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import androidx.navigation.navArgument
import com.rubelz.eisenmatrixtodo.feature_main.presentation.add_task.AddEditTaskScreen
import com.rubelz.eisenmatrixtodo.feature_main.presentation.tasks.TasksScreen
import com.rubelz.eisenmatrixtodo.feature_main.presentation.util.Screen
import com.rubelz.eisenmatrixtodo.ui.theme.EisenMatrixTodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EisenMatrixTodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Screen.TasksScreen.route
                    ) {
                        composable(route = Screen.TasksScreen.route) {
                            TasksScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditTaskScreen.route + "?taskId={taskId}",
                            arguments = listOf(navArgument(name = "taskId") {
                                type = NavType.IntType
                                defaultValue = -1
                            })
                        ) {
                            AddEditTaskScreen(
                                navController = navController,
                            )

                        }
                    }
                }
            }
        }
    }
}
