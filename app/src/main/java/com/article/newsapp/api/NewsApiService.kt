package com.article.newsapp.api

import com.article.newsapp.db.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "tesla",
        @Query("from") from: String = "",
        @Query("sortBy") sortBy: String = "",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}

data class NewsResponse(
    val articles: List<Article>
)