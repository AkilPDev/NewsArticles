package com.article.newsapp.model

import com.article.newsapp.db.Article

data class NewsArticle(
    var isLoading: Boolean = false,
    var articleList: List<Article> = emptyList(),
    var error: String?
)