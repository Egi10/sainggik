package id.buaja.sainggik.feature.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import id.buaja.sainggik.core.designsystem.components.SainggikNavigationBar
import id.buaja.sainggik.core.designsystem.components.SainggikNavigationBarMenu
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import id.buaja.sainggik.feature.root.navigation.BottomNavDestination
import id.buaja.sainggik.feature.root.navigation.RootNavHost

@Composable
internal fun RootRoute(
    navController: NavHostController = rememberNavController()
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route

    val currentParentRoute = remember(currentRoute) {
        currentRoute?.extractParentRoute()
    }

    val shouldShowBottomBar = remember(currentParentRoute) {
        currentParentRoute in BOTTOM_BAR_ROUTES
    }

    Scaffold(
        containerColor = SainggikTheme.colors.background,
        bottomBar = {
            Logger.d {
                "Disini $currentRoute - $currentParentRoute - $BOTTOM_BAR_ROUTES - $shouldShowBottomBar"
            }
            AnimatedVisibility(
                visible = shouldShowBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavigationBar(
                    currentRoute = currentParentRoute,
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        RootNavHost(
            modifier = Modifier
                .padding(innerPadding),
            navHostController = navController
        )
    }
}

@Composable
private fun BottomNavigationBar(
    currentRoute: String?,
    navController: NavHostController
) {
    val menu = BottomNavDestination.entries.map { destination ->
        SainggikNavigationBarMenu(
            icon = destination.icon,
            label = destination.text,
            destination = destination.route
        )
    }

    val selectedMenu = menu.find {
        val routeName = it.destination::class.simpleName
        routeName == currentRoute
    } ?: menu.firstOrNull()

    SainggikNavigationBar(
        items = menu,
        selected = selectedMenu,
        onItemClick = {
            navigateToDestination(
                navController = navController,
                destination = it.destination,
                currentRoute = currentRoute
            )
        }
    )
}

private fun String.extractParentRoute(): String? {
    return runCatching {
        this.substringBefore("?")        // Remove query parameters
            .substringBefore("/")        // Remove nested paths
            .substringAfterLast(".")     // Get last part of class path
    }.getOrNull()
}

private fun navigateToDestination(
    navController: NavHostController,
    destination: Any,
    currentRoute: String?
) {
    val routeName = destination::class.simpleName

    if (currentRoute != routeName) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

private val BOTTOM_BAR_ROUTES = BottomNavDestination.entries
    .map { it.route::class.simpleName }
    .toSet()