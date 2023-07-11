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
import com.example.mysamplekmmapp.android.ui.superheroListing.SuperheroListScreen
import com.example.mysamplekmmapp.android.utils.NavigationRoutes.SuperheroDetailsScreen
import com.example.mysamplekmmapp.android.utils.NavigationRoutes.SuperheroListingScreen
import com.example.mysamplekmmapp.data.mappers.toSuperheroDataHolder
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val viewModel: SuperheroListingViewModel by viewModel()

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

                   NavHost(navController = navController, startDestination = SuperheroListingScreen)
                   {
                       composable(route = SuperheroListingScreen){
                           SuperheroListScreen(
                               viewModel
                           ){
                               viewModel.setSelectedSuperhero(it)
                               navController.navigate(SuperheroDetailsScreen)
                           }
                       }

                       composable(SuperheroDetailsScreen){
                           SuperheroDetailsScreen(
                               vm = viewModel
                           ) {

                           }
                       }
                   }

                }
            }
        }
    }
}



