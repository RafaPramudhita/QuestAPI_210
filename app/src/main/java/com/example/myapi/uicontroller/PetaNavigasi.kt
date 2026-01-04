package com.example.myapi.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapi.uicontroller.route.*
import com.example.myapi.view.*

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = { id ->
                    navController.navigate("${DestinasiDetail.route}/$id")
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // ==== BARU: DETAIL ====
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.itemIdArg) { type = NavType.IntType })
        ) {
            DetailSiswaScreen(
                navigateBack = { navController.popBackStack() },
                navigateToUpdate = { id ->
                    navController.navigate("${DestinasiUpdate.route}/$id")
                }
            )
        }

        // ==== BARU: UPDATE ====
        composable(
            route = DestinasiUpdate.routeWithArgs,
            arguments = listOf(navArgument(DestinasiUpdate.itemIdArg) { type = NavType.IntType })
        ) {
            UpdateSiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
