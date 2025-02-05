package com.article.newsapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.article.newsapp.db.Article

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppNavController(articlesList: List<Article>) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "News App") },
                navigationIcon = (if (currentRoute == Screen.NewsDetailScreen.route) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                } else {
                    {
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Default.Home, contentDescription = "Back")
                        }
                    }
                })!!,
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.Black,
                )
            )
        }
    ){ paddingValue ->
    NavHost(navController, startDestination = Screen.NewsListScreen.route, modifier = Modifier.padding(paddingValue)) {
        composable(route = Screen.NewsListScreen.route){
            NewsListScreen( article = articlesList, articleDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("article", it)
                navController.navigate("details")
            })
        }
        composable(route = Screen.NewsDetailScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300, easing = LinearEasing, delayMillis = 100)) + slideIntoContainer(animationSpec = tween(300, easing = EaseIn), towards = AnimatedContentTransitionScope.SlideDirection.Start)
            }, exitTransition = {
                fadeOut(animationSpec = tween(300, easing = LinearEasing, delayMillis = 100)) + slideOutOfContainer(animationSpec = tween(300, easing = EaseIn), towards = AnimatedContentTransitionScope.SlideDirection.End)
            }){
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article") ?: Article(title = "", description = "", url = "", urlToImage = "", publishedAt = "")
            NewsDetailScreen(article = article)
        }
    }
}
}

