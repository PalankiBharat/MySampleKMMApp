package com.example.mysamplekmmapp.android.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val currentRoute = currentRoute(navController)

    if (NavigationRoutes.listOfBottomNavigationScreens.any{it.route == currentRoute})
    {
        BottomNavigation(
            modifier = Modifier.padding(10.dp).clip(
                RoundedCornerShape(20.dp)
            ).alpha(0.85f),
            backgroundColor = Color.Black
        ) {
            Card (
                backgroundColor = Color.Transparent
            ){
                Row {
                    NavigationRoutes.listOfBottomNavigationScreens.forEach { screen->
                        BottomNavigationItem(
                            icon = {
                                Icon(imageVector =  screen.icon, contentDescription = screen.title, tint = if (currentRoute == screen.route) Color.Red else Color.White)
                            },
                            label = { Text(screen.title) },
                            selected = currentRoute == screen.route,
                            alwaysShowLabel = false,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route)
                                }
                            }

                        )
                    }
                }
            }

        }
    }

}

@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun BottomNavPrev() {
    BottomNavigationBar(rememberNavController())
}