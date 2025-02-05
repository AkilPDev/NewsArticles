package com.article.newsapp.screen

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.article.newsapp.NewsApplication
import com.article.newsapp.NewsViewModel
import com.article.newsapp.R
import com.article.newsapp.db.Article
import com.article.newsapp.utils.Common.formatPublishedDate
import com.article.newsapp.utils.DateTimeUtils


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(article: List<Article>, articleDetails: (Article) -> Unit ) {

            LazyColumn {
                items(article) { article ->
                    NewsItem(article, articleDetails)
                    Divider(color = Color.LightGray, thickness = 0.5.dp)
                }
            }



}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsItem(article: Article, articleDetails: (Article) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { articleDetails(article) }
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
            error = painterResource(id = R.drawable.placeholder)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = article.title, fontWeight = FontWeight.Bold)
            article.description?.let { Text(text = it, maxLines = 2, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodySmall) }
            Text(text = article.publishedAt.formatPublishedDate() , fontWeight = FontWeight.Light, fontSize = 10.sp, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
        }


    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NewList(){
    var listArticle = listOf(Article(id=1,
        title = "Trump’s Transportation Nominee Claims He’ll Let the Investigation Into Musk’s Tesla Continue",
        description = "Will Elon Musk's Tesla troubles vanish once Trump finds his way back to D.C.?",
        url = "https://gizmodo.com/trumps-transportation-nominee-claims-hell-let-the-investigation-into-musks-tesla-continue-2000550956",
        urlToImage = "https://media.wired.com/photos/67a105e44f0b06eb394294dd/191:100/w_1280,c_limit/Politics_Elon_GettyImages-2194353767.jpg",
        publishedAt = "2025-01-16T14:50:20Z"))

    NewsListScreen(article = listArticle) {}
}