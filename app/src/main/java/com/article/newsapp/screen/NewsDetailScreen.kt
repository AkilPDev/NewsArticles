package com.article.newsapp.screen

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.article.newsapp.R
import com.article.newsapp.db.Article

@Composable
fun NewsDetailScreen(article: Article) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box (modifier =  Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center){
            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
                    .aspectRatio(1f),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.loading)
            )
        }

        Text(text = article.title, fontWeight = FontWeight.Bold)

        article.description?.let { Text(text = it, textAlign = TextAlign.Justify, modifier = Modifier.verticalScroll(
            rememberScrollState())) }
    }

}