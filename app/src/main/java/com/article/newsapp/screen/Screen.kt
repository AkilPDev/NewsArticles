package com.article.newsapp.screen

sealed class Screen(val route: String) {
    object NewsListScreen: Screen("list")
    object NewsDetailScreen: Screen("details")
}