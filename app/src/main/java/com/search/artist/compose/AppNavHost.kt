package com.search.artist.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.search.artist.presentation.view.ArtistDetailScreen
import com.search.artist.presentation.view.ArtistReleaseScreen
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
            SearchArtistScreen(onNavigateToArtistDetail = {artistId ->
                navController.navigate("artist_detail_screen/$artistId")
            },modifier)
        }

        composable(route ="artist_detail_screen/{artistId}", arguments = listOf(
            navArgument("artistId"){
                type = NavType.IntType
            }
        )) { backStackEntry ->
            val artistId = backStackEntry.arguments?.getInt("artistId") ?: 0
            ArtistDetailScreen(onNavigateToAlbums = {artistId ->
                navController.navigate("artist_release_screen/$artistId")
            },modifier,artistId = artistId)
        }

        composable(
            route = "artist_release_screen/{artistId}",
            arguments = listOf(navArgument("artistId") { type = NavType.IntType })
        ) { backStackEntry ->
            val artistId = backStackEntry.arguments?.getInt("artistId") ?: 0
            ArtistReleaseScreen(artistId = artistId,modifier)
        }
    }
}