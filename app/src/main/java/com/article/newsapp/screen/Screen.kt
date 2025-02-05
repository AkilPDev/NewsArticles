package com.article.newsapp.screen

sealed class Screen(val route: String) {
    data object NewsListScreen: Screen("list")
    data object NewsDetailScreen: Screen("details")
}