package com.francomucci.storitest.ui.screen.onboarding

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
import com.francomucci.storitest.R
import com.francomucci.storitest.common.ErrorField
import com.francomucci.storitest.ui.navigation.CAMERA_SCREEN
import com.francomucci.storitest.ui.navigation.HOME_SCREEN
import com.francomucci.storitest.ui.uikit.components.AppToolbar
import com.francomucci.storitest.ui.uikit.components.CircularLoader
import com.francomucci.storitest.ui.uikit.components.StoriTextField
import com.francomucci.storitest.ui.uikit.theme.HUGE_DIMEN
import com.francomucci.storitest.ui.uikit.toButtonModifier
import com.francomucci.storitest.ui.uikit.toSmallSpacer
import com.francomucci.storitest.ui.uikit.toTextFieldModifier
import com.francomucci.storitest.ui.uikit.toToolbarModifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OnboardingScreen(
    navigate: (String) -> Unit,
    modifier: Modifier,
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)

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
            title = stringResource(id = R.string.onboarding),
            navController = navController,
            modifier = modifier.toToolbarModifier(),
        )
        Spacer(modifier = modifier.toSmallSpacer())
        Image(
            painter = painterResource(R.drawable.ic_stori_logo),
            modifier = modifier
                .size(HUGE_DIMEN),
            contentDescription = stringResource(id = R.string.app_logo_content_description),
        )
        Spacer(modifier = modifier.toSmallSpacer())
        if (viewModel.loadingState.value) {
            CircularLoader()
        } else {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                StoriTextField(
                    value = viewModel.uiState.value.name,
                    modifier = modifier.toTextFieldModifier(),
                    isError = viewModel.errorFieldState.value != null
                            && viewModel.errorFieldState.value !is ErrorField.Unknown,
                    errorMessage = stringResource(id = R.string.email_error),
                    onValueChange = { viewModel.onNameChange(it) },
                    label = stringResource(id = R.string.name),
                )

                StoriTextField(
                    value = viewModel.uiState.value.lastname,
                    modifier = modifier.toTextFieldModifier(),
                    isError = viewModel.errorFieldState.value != null
                            && viewModel.errorFieldState.value !is ErrorField.Unknown,
                    errorMessage = stringResource(id = R.string.empty_password_error),
                    onValueChange = { viewModel.onLastnameChange(it) },
                    label = stringResource(id = R.string.lastname),
                )

                viewModel.errorFieldState.value?.let { _ ->
                    Text(text = stringResource(id = R.string.please_complete_all_values))
                }

                Button(
                    onClick = {
                        if (cameraPermissionState.status.isGranted) {
                            navigate(CAMERA_SCREEN)
                        } else {
                            cameraPermissionState.launchPermissionRequest()
                        }
                    },
                    modifier = modifier.toButtonModifier(),
                ) {
                    Text(text = stringResource(id = R.string.take_id_picture))
                }
            }
            Button(
                onClick = {
                    viewModel.saveUser(navigate)
                },
                modifier = modifier.toButtonModifier(),
            ) {
                Text(text = stringResource(id = R.string.send_information))
            }
        }
        Spacer(modifier = modifier.toSmallSpacer())
    }
}