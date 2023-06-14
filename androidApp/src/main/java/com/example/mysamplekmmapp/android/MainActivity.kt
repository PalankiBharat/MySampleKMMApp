package com.example.mysamplekmmapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mysamplekmmapp.android.ui.superheroDetails.SuperheroDetailsScreen
import com.example.mysamplekmmapp.android.ui.superheroDetails.toSuperheroDataHolder
import com.example.mysamplekmmapp.android.ui.superheroListing.SuperheroListScreen
import com.example.mysamplekmmapp.android.utils.NavigationRoutes.SuperheroDetailsScreen
import com.example.mysamplekmmapp.android.utils.NavigationRoutes.SuperheroListingScreen
import com.example.mysamplekmmapp.android.utils.deserialize
import com.example.mysamplekmmapp.android.utils.serialize
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                   NavHost(navController = navController, startDestination = "$SuperheroListingScreen/{data}")
                   {
                       composable("$SuperheroListingScreen/{data}"){
                           SuperheroListScreen{
                               navController.navigate("$SuperheroDetailsScreen/${it.serialize()}")
                           }
                       }

                       composable(SuperheroDetailsScreen){
                           val superHero = it.arguments?.getString("data")?.deserialize<SuperheroListResponseItem>().toSuperheroDataHolder()
                           SuperheroDetailsScreen(
                               superhero = superHero
                           ) {

                           }
                       }
                   }

                }
            }
        }
    }
}



