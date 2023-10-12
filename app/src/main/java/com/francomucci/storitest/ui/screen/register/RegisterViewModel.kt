package com.francomucci.storitest.ui.screen.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francomucci.storitest.common.ErrorField
import com.francomucci.storitest.domain.usecases.RegisterUseCase
import com.francomucci.storitest.common.isValidEmail
import com.francomucci.storitest.ui.navigation.ONBOARDING_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    var uiState = mutableStateOf(RegisterUiState())
        private set
    var errorFieldState = mutableStateOf<ErrorField?>(null)
        private set
    var loadingState = mutableStateOf(false)
        private set

    private val mail
        get() = uiState.value.mail

    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(mail = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRegisterRequested(navigate: (String) -> Unit) {
        loadingState.value = true
        if (!mail.isValidEmail()) {
            errorFieldState.value = ErrorField.Mail
            loadingState.value = false
            return
        }

        if (password.isBlank()) {
            errorFieldState.value = ErrorField.Password
            loadingState.value = false
            return
        }
        viewModelScope.launch(
            CoroutineExceptionHandler { _, _ ->
                errorFieldState.value = ErrorField.Unknown
            },
            block = {
                registerUseCase.register(mail, password)
                loadingState.value = false
                navigate(ONBOARDING_SCREEN)
            }
        )
    }
}