package com.article.newsapp

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.article.newsapp.db.Article
import com.article.newsapp.screen.NewsDetailScreen
import com.article.newsapp.screen.NewsListScreen
import dagger.hilt.android.HiltAndroidApp
import org.junit.Rule
import org.junit.Test

class NewList {

    @get: Rule
    val rule = createComposeRule()

    @Test
    fun newList_Display(){
        rule.setContent {
            NewsListScreen( article = listOf(Article(id = 0, title = "Why is blink stock dropping?", description = "Why is Blink Charging Stock Dropping? Blink Charging, a leading electric vehicle (EV) manufacturer, has been experiencing a significant decline […]\\nThe post Why is blink stock dropping? appeared first on Life Sciences World.", url = "https://lifesciencesworld.com/why-is-blink-stock-dropping/", urlToImage = "https://lifesciencesworld.com/wp-content/uploads/2024/12/featured.png", publishedAt = "2025-02-04T05:02:55Z")) ) {
            }
        }

        rule.waitForIdle()

        rule.onNodeWithText("Why is blink stock dropping?").assertExists()
    }


    @Test
    fun newList_Details_Display(){
        rule.setContent {
            NewsDetailScreen( article = Article(id = 0,
                title = "Why is blink stock dropping?",
                description = "Why is Blink Charging Stock Dropping? Blink Charging, a leading electric vehicle (EV) manufacturer, has been experiencing a significant decline […]\\nThe post Why is blink stock dropping? appeared first on Life Sciences World.",
                url = "https://lifesciencesworld.com/why-is-blink-stock-dropping/",
                urlToImage = "https://lifesciencesworld.com/wp-content/uploads/2024/12/featured.png",
                publishedAt = "2025-02-04T05:02:55Z" ))

        }

        rule.waitForIdle()

        rule.onNodeWithText("Why is blink stock dropping?").assertExists()
    }

}