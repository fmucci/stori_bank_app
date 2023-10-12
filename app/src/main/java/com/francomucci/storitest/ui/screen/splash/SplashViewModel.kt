package com.francomucci.storitest.ui.screen.splash

import androidx.lifecycle.ViewModel
import com.francomucci.storitest.domain.usecases.GetLoggedStatusUseCase
import com.francomucci.storitest.ui.navigation.HOME_SCREEN
import com.francomucci.storitest.ui.navigation.LOGIN_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getLoggedStatusUseCase: GetLoggedStatusUseCase,
) : ViewModel() {

    fun onAppStart(navigate: (String) -> Unit) {
        navigate(
            if (getLoggedStatusUseCase.isLogged()) HOME_SCREEN else LOGIN_SCREEN
        )
    }
}
