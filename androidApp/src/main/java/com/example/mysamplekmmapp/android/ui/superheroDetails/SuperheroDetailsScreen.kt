package com.example.mysamplekmmapp.android.ui.superheroDetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mysamplekmmapp.android.utils.Colors
import com.example.mysamplekmmapp.android.utils.Colors.appPrimaryColor
import com.example.mysamplekmmapp.android.utils.MockApis.MockSuperheroDetails
import com.example.mysamplekmmapp.android.utils.deserialize
import com.example.mysamplekmmapp.data.model.Appearance
import com.example.mysamplekmmapp.data.model.Biography
import com.example.mysamplekmmapp.data.model.Powerstats
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.skydoves.cloudy.Cloudy

@Composable
fun SuperheroDetailsScreen(
    modifier: Modifier = Modifier,
    superhero: SuperheroDetailsDataHolder,
    navigateBack: () -> Unit,
) {

    val scrollState = rememberScrollState()
    val strength: Float by animateFloatAsState(superhero.strength.toFloat(), label = "Strength")
    val speed: Float by animateFloatAsState(superhero.speed.toFloat(), label = "speed")
    val combat: Float by animateFloatAsState(superhero.combat.toFloat(), label = "combat")
    val power: Float by animateFloatAsState(superhero.power.toFloat(), label = "power")
    val intelligence: Float by animateFloatAsState(
        superhero.intelligence.toFloat(),
        label = "intelligence"
    )

    Column(
        modifier
            .fillMaxSize()
            .background(appPrimaryColor)
            .alpha(0.8f)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f)
        ) {
            Cloudy(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = superhero.imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .align(Alignment.TopCenter)
                )
            }

            AsyncImage(
                model = superhero.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(0.85f)
                    .clip(RoundedCornerShape(20.dp))
                    .shadow(elevation = 10.dp)
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
            Text(
                text = "Height : ${superhero.height}",
                color = Color.White,
                fontSize = 18.sp,
            )
            Text(
                text = "Weight : ${superhero.weight}",
                color = Color.White,
                fontSize = 18.sp,
            )
        }

        SuperheroStatsIndicator(
            strength,
            Color(0xFFF44336),
            "Strength"
        )
        SuperheroStatsIndicator(
            power,
            Color(0xFF9C27B0),
            "Power"
        )
        SuperheroStatsIndicator(
            speed,
            Color(0xFFFFEB3B),
            "Speed"
        )
        SuperheroStatsIndicator(
            intelligence,
            Color(0xFF03A9F4),
            "Intelligence"
        )
        SuperheroStatsIndicator(
            combat,
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
    Text(text = "$label $percent", color = Color.White, modifier = Modifier.padding(top = 15.dp))
    LinearProgressIndicator(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 5.dp)
            .height(20.dp)
            .fillMaxWidth(),
        progress = percent / 100,
        color = color,
        backgroundColor = Color.DarkGray,
        strokeCap = StrokeCap.Round
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewSuperheroDetailsScreen() {
    val response: SuperheroListResponseItem = MockSuperheroDetails.deserialize()
    SuperheroDetailsScreen(
        superhero = response.toSuperheroDataHolder(),
        modifier = Modifier.fillMaxSize()
    ) {}
}

data class SuperheroDetailsDataHolder(
    val name: String,
    val bio: String,
    val imageUrl: String,
    val strength: Int,
    val durability: Int,
    val combat: Int,
    val power: Int,
    val speed: Int,
    val intelligence: Int,
    val weight: String,
    val height: String,
)

fun SuperheroListResponseItem?.toSuperheroDataHolder(): SuperheroDetailsDataHolder {
    val alisases =
        if (this?.biography?.aliases?.isEmpty() != true) "also known as " + this?.biography?.aliases?.joinToString() else ""
    return SuperheroDetailsDataHolder(
        name = this?.name ?: "No Name Found",
        bio = "${this?.name}${this?.biography?.fullName?.let { " aka $it" } ?: ""} $alisases was born at ${this?.biography?.placeOfBirth ?: "New Jersy"}",
        imageUrl = this?.images?.lg
            ?: "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/2-abe-sapien.jpg",
        strength = this?.powerstats?.strength ?: 0,
        durability = this?.powerstats?.durability ?: 0,
        combat = this?.powerstats?.combat ?: 0,
        power = this?.powerstats?.power ?: 0,
        speed = this?.powerstats?.speed ?: 0,
        intelligence = this?.powerstats?.intelligence ?: 0,
        weight = this?.appearance?.weight?.getOrElse(1) { "" } ?: "No Weight Records Found",
        height = this?.appearance?.height?.getOrElse(1) { "" } ?: "No Height Records Found"
    )
}