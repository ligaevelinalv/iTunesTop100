package com.example.itunestoptask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.itunestoptask.presentation.AlbumScreen
import com.example.itunestoptask.presentation.AlbumViewModel
import com.example.itunestoptask.presentation.DetailsScreen
import kotlinx.serialization.Serializable

@Composable
fun AlbumNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AlbumRoute
    ) {

        navigation<AlbumRoute>(
            startDestination = AlbumDestination::class
        ) {
            composable<AlbumDestination> { backStackEntry ->
                val graphScope = remember(backStackEntry) {
                    navController.getBackStackEntry(
                        AlbumRoute
                    )
                }
                val viewModel: AlbumViewModel = hiltViewModel(graphScope)

                AlbumScreen(
                    viewModel = viewModel,
                    onItemClicked = { albumId ->
                        navController.navigate(DetailsDestination(albumId = albumId))
                    }
                )
            }

            composable<DetailsDestination> { backStackEntry ->
                val graphScope = remember(backStackEntry) {
                    navController.getBackStackEntry(
                        AlbumRoute
                    )
                }
                val viewModel: AlbumViewModel = hiltViewModel(graphScope)

                val details: DetailsDestination = backStackEntry.toRoute()

                DetailsScreen(
                    viewModel = viewModel,
                    albumId = details.albumId,
                    onNavigateBack = navController::navigateUp
                )
            }
        }
    }
}

@Serializable
object AlbumRoute

@Serializable
object AlbumDestination

@Serializable
class DetailsDestination(val albumId: String)
