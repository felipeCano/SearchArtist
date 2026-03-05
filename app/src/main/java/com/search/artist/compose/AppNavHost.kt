package com.search.artist.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.search.artist.presentation.view.SearchArtistScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "search_artist_screen"
    ){
        composable("search_artist_screen"){
            SearchArtistScreen(modifier)
        }
    }
}