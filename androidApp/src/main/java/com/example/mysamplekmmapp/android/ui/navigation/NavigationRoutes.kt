package com.example.mysamplekmmapp.android.ui.navigation

import com.example.mysamplekmmapp.android.ui.navigation.BottomNavigationScreens.*

object NavigationRoutes {
    const val DASHBOARD = "home"
    const val SUPERHERO_DETAILS_SCREEN = "superheroDetailsScreen"
    const val SEARCH = "search"
    const val FAVORITES = "favorite"
    const val ABOUT = "about"
    val listOfBottomNavigationScreens = listOf(
        Home, Search,Favorites,About
    )

}