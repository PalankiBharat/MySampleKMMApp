package com.example.mysamplekmmapp.android.ui.superheroDetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysamplekmmapp.android.R
import com.example.mysamplekmmapp.android.utils.Colors.appPrimaryColor
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuperheroDetailsScreen(
    modifier: Modifier = Modifier,
    vm: SuperheroListingViewModel = koinViewModel(),
    navController: NavController,
    id:Int?
) {
    val superhero = vm.getSuperheroById(id?:0)
    val scrollState = rememberScrollState()

    Column(
        modifier
            .fillMaxSize()
            .background(
                appPrimaryColor
            )
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f)
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .align(Alignment.TopCenter),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds
                ),
                imageModel = { superhero.imageUrl },
                component = rememberImageComponent {
                    +BlurTransformationPlugin(radius = 15)
                    +ShimmerPlugin(baseColor = Color.DarkGray, highlightColor = Color.Gray)
                }
            )

            CoilImage(
                imageModel = { superhero.imageUrl },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.56f)
                    .fillMaxHeight(0.85f)
                    .clip(RoundedCornerShape(20.dp))
                    .shadow(elevation = 10.dp),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds
                )
            )
        }

        Text(
            text = superhero.name,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "BIO",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = superhero.bio,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.height_logo),
                    contentDescription = "Weight Logo",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Height : ${superhero.height}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }

            Column {
                Image(
                    painter = painterResource(id = R.drawable.weight_logo),
                    contentDescription = "Weight Logo",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Weight : ${superhero.weight}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        }

        SuperheroStatsIndicator(
            superhero.strength.toFloat(),
            Color(0xFFF44336),
            "Strength"
        )
        SuperheroStatsIndicator(
            superhero.power.toFloat(),
            Color(0xFF9C27B0),
            "Power"
        )
        SuperheroStatsIndicator(
            superhero.speed.toFloat(),
            Color(0xFFFFEB3B),
            "Speed"
        )
        SuperheroStatsIndicator(
            superhero.intelligence.toFloat(),
            Color(0xFF03A9F4),
            "Intelligence"
        )
        SuperheroStatsIndicator(
            superhero.combat.toFloat(),
            Color(0xFF388E3C),
            "Combat"
        )
    }
}

@Composable
fun SuperheroStatsIndicator(
    percent: Float,
    color: Color,
    label: String = ""
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage by animateFloatAsState(
        targetValue =
        if (animationPlayed) percent else 0f,
        animationSpec = tween(durationMillis = 2000), label = label
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Text(text = "$label $percent", color = Color.White, modifier = Modifier.padding(top = 15.dp))
    LinearProgressIndicator(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 5.dp)
            .height(20.dp)
            .fillMaxWidth(),
        progress = currentPercentage / 100,
        color = color,
        backgroundColor = Color.DarkGray,
        strokeCap = StrokeCap.Round
    )
}




