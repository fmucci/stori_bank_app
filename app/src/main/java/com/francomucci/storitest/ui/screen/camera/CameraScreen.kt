package com.francomucci.storitest.ui.screen.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.francomucci.storitest.R
import com.francomucci.storitest.ui.uikit.components.AppToolbar
import com.francomucci.storitest.ui.uikit.toToolbarModifier

@Composable
fun CameraScreen(
    navigate: (String) -> Unit,
    modifier: Modifier,
    navController: NavController,
) {
    AppToolbar(
        title = stringResource(id = R.string.camera),
        modifier = modifier.toToolbarModifier(),
        navController = navController,
    )
}