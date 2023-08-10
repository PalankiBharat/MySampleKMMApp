package com.example.mysamplekmmapp.android.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mysamplekmmapp.android.ui.superheroDetails.SuperheroDetailsScreen
import com.example.mysamplekmmapp.android.ui.superheroListing.SuperheroListScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.DASHBOARD
            )
            {

                composable(route = NavigationRoutes.DASHBOARD) {
                    SuperheroListScreen(navController = navController)
                }

                composable(
                    route = NavigationRoutes.SUPERHERO_DETAILS_SCREEN.plus("/{superhero_id}"),
                    arguments = listOf(
                        navArgument("superhero_id") {
                            type = NavType.IntType
                        })
                ) {
                    val id = it.arguments?.getInt("superhero_id")
                    SuperheroDetailsScreen(
                        id = id
                    )
                }

                composable(NavigationRoutes.SEARCH){

                }

                composable(NavigationRoutes.FAVORITES){

                }

                composable(NavigationRoutes.ABOUT){

                }
            }
        }
    }

}