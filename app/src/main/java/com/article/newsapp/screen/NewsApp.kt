package com.article.newsapp.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.article.newsapp.NewsViewModel
import com.article.newsapp.db.Article
import com.article.newsapp.model.NewsArticle

@Composable
fun NewsApp(articlesList: List<Article>) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.NewsListScreen.route) {
//        composable("list") { NewsListScreen( navController, articlesList) }
//        composable("details/{article}") { backStackEntry ->
//            val article = backStackEntry.arguments?.getString("article") ?: ""
//            NewsDetailScreen(article)
//        }
      /*  composable("list"){
            NewsListScreen(navController = navController, article = articlesList, articleDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("article", it)
                navController.navigate("details")
            })
        }
        composable("details"){
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article") ?: Article(title = "", description = "", url = "", urlToImage = "", publishedAt = "")
            NewsDetailScreen(article = article)
        }*/
        composable(route = Screen.NewsListScreen.route){
            NewsListScreen(navController = navController, article = articlesList, articleDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("article", it)
                navController.navigate("details")
            })
        }
        composable(route = Screen.NewsDetailScreen.route){
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article") ?: Article(title = "", description = "", url = "", urlToImage = "", publishedAt = "")
            NewsDetailScreen(article = article)
        }
    }
}

