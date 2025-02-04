package com.article.newsapp.screen

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.article.newsapp.NewsApplication
import com.article.newsapp.NewsViewModel
import com.article.newsapp.R
import com.article.newsapp.db.Article

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(navController: NavController, article: List<Article>, articleDetails: (Article) -> Unit ) {

            LazyColumn {
                items(article) { article ->
                    println("NewListScreen: $article")
                    NewsItem(article, articleDetails)
                  /*  NewsItem(article) {
//                        navController.navigate("details/${article}")
                    }*/
                }
            }



}

@Composable
fun NewsItem(article: Article, articleDetails: (Article) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { articleDetails(article) }
//            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(85.dp),
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.loading)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = article.title, fontWeight = FontWeight.Bold)
            article.description?.let { Text(text = it, maxLines = 2, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodySmall) }
        }
    }
}