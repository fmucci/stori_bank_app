package com.francomucci.storitest.ui.screen.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.francomucci.storitest.common.ErrorField
import com.francomucci.storitest.domain.usecases.SaveUserUseCase
import com.francomucci.storitest.domain.model.StoriUser
import com.francomucci.storitest.ui.navigation.HOME_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    var uiState = mutableStateOf(OnboardingUiState())
        private set
    var errorFieldState = mutableStateOf<ErrorField?>(null)
        private set
    var loadingState = mutableStateOf(false)
        private set

    private val name
        get() = uiState.value.name

    private val lastname
        get() = uiState.value.lastname

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onLastnameChange(newValue: String) {
        uiState.value = uiState.value.copy(lastname = newValue)
    }

    fun saveUser(navigate: (String) -> Unit) {
        loadingState.value = true
        if (name.isBlank() || lastname.isBlank()) {
            errorFieldState.value = ErrorField.All
            return
        }

        runBlocking {
            withContext(Dispatchers.IO) {
                saveUserUseCase.saveUser(
                    storiUser = StoriUser(
                        name = name,
                        lastname = lastname,
                    ),
                    onSuccessListener = {
                        loadingState.value = false
                        navigate(HOME_SCREEN)
                    },
                    onFailureListener = { _ ->
                        errorFieldState.value = ErrorField.Unknown
                        loadingState.value = false
                    })
            }
        }
    }
}