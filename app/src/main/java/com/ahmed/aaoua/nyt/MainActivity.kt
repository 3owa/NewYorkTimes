package com.ahmed.aaoua.nyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmed.aaoua.nyt.screens.DetailsScreen
import com.ahmed.aaoua.nyt.screens.HomeScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //UI
            window.statusBarColor = Color.White.toArgb()
            window.navigationBarColor = Color.White.toArgb()
            window.decorView.isVerticalScrollBarEnabled = true

            val systemUiController = rememberSystemUiController()
            systemUiController.navigationBarDarkContentEnabled = true
            systemUiController.statusBarDarkContentEnabled = true

            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = "home"){

                composable(
                    route = "home"
                ){
                    HomeScreen(navController)
                }
                composable(
                    route = "details/{img}/{title}/{date}/{keywords}/{description}",
                ){
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val imgUrl = navBackStackEntry?.arguments?.getString("img") ?: ""
                    val title = navBackStackEntry?.arguments?.getString("title") ?: ""
                    val date = navBackStackEntry?.arguments?.getString("date") ?: ""
                    val keywords = navBackStackEntry?.arguments?.getString("keywords") ?: ""
                    val description = navBackStackEntry?.arguments?.getString("description") ?: ""

                    DetailsScreen(
                        navController = navController,
                        imgUrl = imgUrl,
                        title = title,
                        date = date,
                        keywords = keywords,
                        description = description
                    )
                }
            }
        }
    }
}
