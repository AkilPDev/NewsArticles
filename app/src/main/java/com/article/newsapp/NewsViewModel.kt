package com.article.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.article.newsapp.db.Article
import com.article.newsapp.model.NewsArticle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    private val _news = MutableStateFlow<NewsArticle>(NewsArticle(isLoading = true, articleList = emptyList(), error = null))
    val news: StateFlow<NewsArticle> = _news

    fun fetchNews(apiKey: String) {
        _news.value = NewsArticle(isLoading=true,  error=null)
//        viewModelScope.launch {
//            repository.getNews(apiKey).collect {
//
//                _news.value = NewsArticle(isLoading=false, articleList= it, error=null)
//            }
//        }
        viewModelScope.launch {
            repository.getNews(apiKey).collect { result ->
                result.fold(
                    onSuccess = { articles ->
                        _news.value = NewsArticle(isLoading = false, articleList = articles, error = null)
                    },
                    onFailure = { error ->
                        _news.value = NewsArticle(isLoading = false, articleList = emptyList(), error = error.message ?: "Unknown error occurred")
                    }
                )
            }
        }
    }
}

