package com.ahmed.aaoua.nyt.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import java.net.URLDecoder
import java.net.URLEncoder

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreen(navController: NavController,imgUrl:String,title:String,date:String,keywords:String,description:String) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {

        Box{
            if (imgUrl != " ") {
                Image(
                    painter = rememberImagePainter(data = URLDecoder.decode(imgUrl, "UTF-8")),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }

            Card(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color.White),
            ){
                IconButton(onClick = {navController.popBackStack()}) {

                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }

            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {


            Text(
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(10.dp),
            )

            Text(
                text = date,
                color = Color.Gray,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(10.dp)
            )

            FlowRow() {

                keywords.split(";").forEach {
                    Card(
                        modifier = Modifier
                            .padding(5.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.Black)
                    ) {

                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = it,
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(5.dp),
                            )

                        }

                    }
                }

            }

            Divider(modifier = Modifier.padding(vertical = 5.dp))


            Text(
                text = description,
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(10.dp),
            )

        }
    }
}