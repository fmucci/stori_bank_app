package com.francomucci.storitest.ui.screen.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.francomucci.storitest.R
import com.francomucci.storitest.ui.uikit.components.AppToolbar
import com.francomucci.storitest.ui.uikit.components.CircularLoader
import com.francomucci.storitest.ui.uikit.components.StoriText
import com.francomucci.storitest.ui.uikit.theme.FONT_HUGE
import com.francomucci.storitest.ui.uikit.theme.FONT_LARGE
import com.francomucci.storitest.ui.uikit.theme.FONT_NORMAL
import com.francomucci.storitest.ui.uikit.toSmallSpacer
import com.francomucci.storitest.ui.uikit.toToolbarModifier

@Composable
fun TransactionDetailScreen(
    navigate: (String) -> Unit,
    modifier: Modifier,
    navController: NavController,
    id: String,
    viewModel: TransactionDetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getTransaction(id)
    })
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppToolbar(
            title = stringResource(id = R.string.detail),
            modifier = modifier.toToolbarModifier(),
            navController = navController,
        )
        Spacer(modifier = modifier.toSmallSpacer())
        if (viewModel.loadingState.value) {
            CircularLoader()
        } else {
            with(viewModel.transactionState.value) {
                StoriText(
                    text = title,
                    modifier = modifier,
                    size = FONT_LARGE,
                    textAlign = TextAlign.Center,
                )
                StoriText(
                    text = value.toString(),
                    modifier = modifier,
                    size = FONT_HUGE,
                    bold = true,
                    textAlign = TextAlign.Center,
                )
                StoriText(
                    text = date,
                    modifier = modifier,
                    size = FONT_NORMAL,
                    textAlign = TextAlign.Center,
                )
            }

        }
        Spacer(modifier = modifier.toSmallSpacer())
    }
}