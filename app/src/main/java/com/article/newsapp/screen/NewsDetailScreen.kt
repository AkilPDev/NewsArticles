package com.article.newsapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.article.newsapp.R
import com.article.newsapp.db.Article
import com.article.newsapp.utils.Common.formatPublishedDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsDetailScreen(article: Article) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {

        Box (modifier =  Modifier.fillMaxWidth().height(250.dp),
            contentAlignment = Alignment.Center, ){
            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        Text(text = article.publishedAt.formatPublishedDate() , fontWeight = FontWeight.Light, fontSize = 10.sp, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth().padding(8.dp))


        Text(text = article.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))

        article.description?.let { Text(text = it, textAlign = TextAlign.Justify, modifier = Modifier.padding(8.dp).verticalScroll(
            rememberScrollState())) }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DetailsPrview(){
    var article = Article(id=1,
        title = "Trump’s Transportation Nominee Claims He’ll Let the Investigation Into Musk’s Tesla Continue",
        description = "Will Elon Musk's Tesla troubles vanish once Trump finds his way back to D.C.?",
        url = "https://gizmodo.com/trumps-transportation-nominee-claims-hell-let-the-investigation-into-musks-tesla-continue-2000550956",
        urlToImage = "https://media.wired.com/photos/67a105e44f0b06eb394294dd/191:100/w_1280,c_limit/Politics_Elon_GettyImages-2194353767.jpg",
        publishedAt = "2025-01-16T14:50:20Z")
    NewsDetailScreen(article = article)
}
