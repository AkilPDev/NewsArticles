package com.article.newsapp

import com.article.newsapp.api.NewsApiService
import com.article.newsapp.db.NewsDatabase
import com.article.newsapp.db.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    private val db: NewsDatabase
) {
//    suspend fun getNews(apiKey: String): Flow<List<Article>> = flow {
//
//        try {
//
//
//            val response = api.getNews(apiKey = apiKey)
//
//            withContext(Dispatchers.IO){
//            db.newsDao().insertAll(response.articles)
//            }
//
//
//            emit(response.articles)
//        } catch (e: IOException) {
//
//            db.newsDao().getAllArticles().collect{
//                emit(it)
//            }
//        } catch (e: HttpException) {
//            println("getNewResponse HttpException: ${e.message}")
//
//            db.newsDao().getAllArticles().collect{
//                emit(it)
//            }
//        }
//    }
suspend fun getNews(apiKey: String): Flow<Result<List<Article>>> = flow {
    try {
        val response = api.getNews(apiKey=apiKey)

        withContext(Dispatchers.IO) {
            db.newsDao().insertAll(response.articles)
        }

        emit(Result.success(response.articles))  // Success case
    } catch (e: IOException) {
        db.newsDao().getAllArticles().collect {
            emit(Result.success(it))  // Emit cached data in case of network error
        }
        emit(Result.failure(e)) // Emit failure message
    } catch (e: HttpException) {
        db.newsDao().getAllArticles().collect {
            emit(Result.success(it))
        }
        emit(Result.failure(e)) // Emit failure message
    }
}
}