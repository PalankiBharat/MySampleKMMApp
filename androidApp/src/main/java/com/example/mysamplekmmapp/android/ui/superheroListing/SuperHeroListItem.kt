package com.example.mysamplekmmapp.android.ui.superheroListing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun SuperheroCard(modifier: Modifier = Modifier, imageUrl: String, title: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        CoilImage(
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop
            ),
            modifier = Modifier
                .fillMaxSize(),
            component = rememberImageComponent {
                +ShimmerPlugin(baseColor = Color.DarkGray, highlightColor = Color.Gray)
            }
        )

        // Create the linear gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 50f,
                        endY = 1000f
                    )
                )
        )

        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun SuperHeroCardPreview() {
    SuperheroCard(imageUrl = "", title = "Bharat")
}