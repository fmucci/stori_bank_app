package com.francomucci.storitest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.francomucci.storitest.ui.Screen
import com.francomucci.storitest.ui.screen.camera.CameraScreen
import com.francomucci.storitest.ui.screen.home.HomeScreen
import com.francomucci.storitest.ui.screen.login.LoginScreen
import com.francomucci.storitest.ui.screen.onboarding.OnboardingScreen
import com.francomucci.storitest.ui.screen.register.RegisterScreen
import com.francomucci.storitest.ui.screen.splash.SplashScreen
import com.francomucci.storitest.ui.screen.transaction.TransactionDetailScreen

fun NavGraphBuilder.createNavGraph(
    navController: NavController,
    modifier: Modifier,
    screen: Screen,
) {
    composable(screen.route) {
        val navigation: (String) -> Unit = { route -> navController.navigate(route) }

        when (screen) {
            Screen.Home -> HomeScreen(
                navigate = navigation,
                modifier = modifier,
            )

            Screen.Camera -> CameraScreen(
                navigate = navigation,
                modifier = modifier,
                navController = navController
            )

            Screen.Login -> LoginScreen(
                navigate = navigation,
                modifier = modifier,
                navController = navController
            )

            Screen.Register -> RegisterScreen(
                navigate = navigation,
                modifier = modifier,
                navController = navController
            )

            Screen.Splash -> SplashScreen(navigate = navigation, modifier = modifier)
            Screen.Onboarding -> OnboardingScreen(
                navigate = navigation,
                modifier = modifier,
                navController = navController
            )

            Screen.TransactionDetail -> TransactionDetailScreen(
                navigate = navigation,
                modifier = modifier,
                id = it.arguments?.getString(TRANSACTION_DETAIL_ID_PARAM) ?: "",
                navController = navController,
            )
        }
    }
}

@Composable
fun Navigation(
    modifier: Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        createNavGraph(navController, modifier, Screen.Splash)
        createNavGraph(navController, modifier, Screen.Home)
        createNavGraph(navController, modifier, Screen.Login)
        createNavGraph(navController, modifier, Screen.Camera)
        createNavGraph(navController, modifier, Screen.Register)
        createNavGraph(navController, modifier, Screen.Onboarding)
        createNavGraph(navController, modifier, Screen.TransactionDetail)
    }
}