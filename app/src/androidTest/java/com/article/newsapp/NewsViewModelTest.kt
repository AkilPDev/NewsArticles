package com.article.newsapp

import com.article.newsapp.db.Article
import com.article.newsapp.test.FakeNewsRepository
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    private lateinit var viewModel: NewsViewModel
    private lateinit var fakeRepository: FakeNewsRepository

//    @get:Rule
//    val coroutineRule = MainDispatcherRule()
//
//    @Before
//    fun setup() {
//        fakeRepository = FakeNewsRepository()
//        viewModel = NewsViewModel(fakeRepository)
//    }
//
//    @Test
//    fun `fetchNews success updates news state`() = runTest {
//        val fakeArticles = listOf(
//            Article(
//
//                title = "Why is blink stock dropping?",
//                description = "Blink Charging stock is dropping due to market trends...",
//                url = "https://lifesciencesworld.com/why-is-blink-stock-dropping/",
//                urlToImage = "https://lifesciencesworld.com/wp-content/uploads/2024/12/featured.png",
//                publishedAt = "2025-02-04T05:02:55Z",
//            )
//        )
//
//        fakeRepository.setFakeNewsResponse(fakeArticles)
//
//        viewModel.fetchNews("test_api_key")
//
//        viewModel.news.test {
//            val state = awaitItem()
//            assertEquals(false, state.isLoading)
//            assertEquals(1, state.articleList.size)
//            assertEquals("Why is blink stock dropping?", state.articleList.first().title)
//            assertNull(state.error)
//        }
//    }
//
//    @Test
//    fun `fetchNews failure updates error state`() = runTest {
//        val errorMessage = "Network error"
//        fakeRepository.setFakeErrorResponse(errorMessage)
//
//        viewModel.fetchNews("test_api_key")
//
//        viewModel.news.test {
//            val state = awaitItem()
//            assertEquals(false, state.isLoading)
//            assertEquals(emptyList<Article>(), state.articleList)
//            assertEquals(errorMessage, state.error)
//        }
//    }
}