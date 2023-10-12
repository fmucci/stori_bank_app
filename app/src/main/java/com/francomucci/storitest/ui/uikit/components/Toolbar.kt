package com.francomucci.storitest.ui.uikit.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.francomucci.storitest.R

@Composable
fun AppToolbar(
    title: String,
    navController: NavController? = null,
    actions: (() -> Unit)? = null,
    modifier: Modifier,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = { navController?.let { BackToolbarButton(it) } },
        actions = { actions?.let { LogOutAction(it) } },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.primary_color),
            titleContentColor = colorResource(R.color.grey_900),
        ),
        title = {
            Text(
                text = title,
            )
        }
    )
}

@Composable
fun BackToolbarButton(navController: NavController) {
    if (navController.previousBackStackEntry != null) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }
    }
}

@Composable
fun LogOutAction(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            "Log Out",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppToolbarPreview() {
    AppToolbar(
        title = "Some text",
        modifier = Modifier,
    )
}