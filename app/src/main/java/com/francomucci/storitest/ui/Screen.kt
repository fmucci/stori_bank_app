package com.francomucci.storitest.ui

import com.francomucci.storitest.ui.navigation.CAMERA_SCREEN
import com.francomucci.storitest.ui.navigation.HOME_SCREEN
import com.francomucci.storitest.ui.navigation.LOGIN_SCREEN
import com.francomucci.storitest.ui.navigation.ONBOARDING_SCREEN
import com.francomucci.storitest.ui.navigation.REGISTER_SCREEN
import com.francomucci.storitest.ui.navigation.SPLASH_SCREEN
import com.francomucci.storitest.ui.navigation.TRANSACTION_DETAIL_ID_PARAM
import com.francomucci.storitest.ui.navigation.TRANSACTION_DETAIL_SCREEN

sealed class Screen(val route: String) {
    object Home : Screen(route = HOME_SCREEN)
    object Camera : Screen(route = CAMERA_SCREEN)
    object Login : Screen(route = LOGIN_SCREEN)
    object Register : Screen(route = REGISTER_SCREEN)
    object Splash : Screen(route = SPLASH_SCREEN)
    object Onboarding : Screen(route = ONBOARDING_SCREEN)
    object TransactionDetail : Screen(route = "$TRANSACTION_DETAIL_SCREEN/{$TRANSACTION_DETAIL_ID_PARAM}")
}