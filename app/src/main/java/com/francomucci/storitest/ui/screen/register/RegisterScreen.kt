package com.francomucci.storitest.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.francomucci.storitest.common.ErrorField
import com.francomucci.storitest.R
import com.francomucci.storitest.ui.uikit.components.AppToolbar
import com.francomucci.storitest.ui.uikit.components.CircularLoader
import com.francomucci.storitest.ui.uikit.components.StoriTextField
import com.francomucci.storitest.ui.uikit.theme.HUGE_DIMEN
import com.francomucci.storitest.ui.uikit.toButtonModifier
import com.francomucci.storitest.ui.uikit.toSmallSpacer
import com.francomucci.storitest.ui.uikit.toTextFieldModifier
import com.francomucci.storitest.ui.uikit.toToolbarModifier

@Composable
fun RegisterScreen(
    navigate: (String) -> Unit,
    modifier: Modifier,
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .verticalScroll(
                rememberScrollState()
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppToolbar(
            title = stringResource(id = R.string.register),
            modifier = modifier.toToolbarModifier(),
            navController = navController,
        )
        Spacer(modifier = modifier.toSmallSpacer())
        if (viewModel.loadingState.value) {
            CircularLoader()
        } else {
            Image(
                painter = painterResource(R.drawable.ic_stori_logo),
                modifier = modifier
                    .size(HUGE_DIMEN),
                contentDescription = stringResource(id = R.string.app_logo_content_description),
            )
            Spacer(modifier = modifier.toSmallSpacer())
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                StoriTextField(
                    value = viewModel.uiState.value.mail,
                    modifier = modifier.toTextFieldModifier(),
                    isError = viewModel.errorFieldState.value is ErrorField.Mail,
                    errorMessage = stringResource(id = R.string.email_error),
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = stringResource(id = R.string.mail),
                )

                StoriTextField(
                    value = viewModel.uiState.value.password,
                    modifier = modifier.toTextFieldModifier(),
                    isError = viewModel.errorFieldState.value is ErrorField.Password,
                    errorMessage = "Password should not be empty",
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = stringResource(id = R.string.password),
                )

                viewModel.errorFieldState.value?.let {
                    if (it is ErrorField.Unknown) {
                        Text(text = stringResource(id = R.string.check_credentials_error))
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.onRegisterRequested(navigate)
                },
                modifier = modifier.toButtonModifier(),
            ) {
                Text(text = stringResource(id = R.string.register))
            }
        }
        Spacer(modifier = modifier.toSmallSpacer())
    }
}