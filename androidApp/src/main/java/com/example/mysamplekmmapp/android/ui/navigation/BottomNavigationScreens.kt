package com.example.mysamplekmmapp.android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(val route: String, val title: String,  val icon: ImageVector) {
    data object Home : BottomNavigationScreens(NavigationRoutes.DASHBOARD, "Home" , Icons.Outlined.Home)
    data object Search : BottomNavigationScreens(NavigationRoutes.SEARCH, "Search" ,Icons.Outlined.Search)
    data object Favorites : BottomNavigationScreens(NavigationRoutes.FAVORITES, "Favorites" ,Icons.Outlined.Favorite)
    data object About : BottomNavigationScreens(NavigationRoutes.ABOUT, "About" ,Icons.Outlined.Person)
}