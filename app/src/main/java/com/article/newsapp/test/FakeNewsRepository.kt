package com.article.newsapp.test

import com.article.newsapp.NewsRepository
import com.article.newsapp.api.NewsApiService
import com.article.newsapp.db.Article
import com.article.newsapp.db.NewsDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNewsRepository(api: NewsApiService, db: NewsDatabase) : NewsRepository(api, db) {
    private val newsFlow = MutableStateFlow<Result<List<Article>>>(Result.success(emptyList()))

    override suspend fun getNews(apiKey: String): Flow<Result<List<Article>>> {
        return newsFlow
    }

    fun setFakeNewsResponse(newsArticles: List<Article>) {
        newsFlow.value = Result.success(newsArticles)
    }

    fun setFakeErrorResponse(errorMessage: String) {
        newsFlow.value = Result.failure(Exception(errorMessage))
    }
}