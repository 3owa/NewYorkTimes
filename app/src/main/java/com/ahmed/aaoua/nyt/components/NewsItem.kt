package com.ahmed.aaoua.nyt.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ahmed.aaoua.nyt.api.Article
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItem(navController: NavController,article: Article) {

    val imageUrl = article.media
        .flatMap { it.media_metadata}
        .find { it.width == 440 && it.height == 293 }
        ?.url


    Card(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(Color.LightGray.copy(0.5f)),
        elevation = CardDefaults.cardElevation(0.dp,0.dp,0.dp,0.dp),
        onClick = {navController.navigate("details/${if(imageUrl == null) " " else URLEncoder.encode(imageUrl, "UTF-8")}/${article.title}/${article.published_date}/${article.adx_keywords}/${article.abstract}")}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {


            if (imageUrl != null) {
                Image(
                    painter = rememberImagePainter(data = imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Text(
                text = article.title,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(10.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.published_date,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}
