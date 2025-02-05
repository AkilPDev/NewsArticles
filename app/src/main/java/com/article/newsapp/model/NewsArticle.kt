package com.article.newsapp.model

import com.article.newsapp.db.Article

/**
 * Data class representing the state of a news article list.
 *
 * @property isLoading Indicates whether data is currently being loaded.
 * @property articleList A list of articles fetched from an API or database.
 * @property error A string containing an error message if data fetching fails, otherwise null.
 */
data class NewsArticle(
    var isLoading: Boolean = false,
    var articleList: List<Article> = emptyList(),
    var error: String?
)