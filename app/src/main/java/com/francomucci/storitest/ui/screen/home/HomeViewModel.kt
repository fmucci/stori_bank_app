package com.francomucci.storitest.ui.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.francomucci.storitest.domain.usecases.GetUserUseCase
import com.francomucci.storitest.domain.usecases.LogOutUseCase
import com.francomucci.storitest.domain.model.StoriUser
import com.francomucci.storitest.ui.navigation.SPLASH_SCREEN
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logOutUseCase: LogOutUseCase,
) : ViewModel() {

    var errorMessageState = mutableStateOf("")
        private set

    var user = mutableStateOf(StoriUser())
        private set

    fun getUser() {
        runBlocking {
            withContext(Dispatchers.IO) {
                getUserUseCase.getUser(
                    onSuccessListener = { document: QuerySnapshot ->
                        val userDB = document.documents.firstOrNull()
                        userDB?.let {
                            user.value = it.toObject(StoriUser::class.java)
                                ?: StoriUser()
                        } ?: run {
                            errorMessageState.value = "Something went wrong"
                        }
                    },
                ) { error: Exception ->
                    errorMessageState.value = error.message ?: ""
                }
            }
        }
    }

    fun logOut(navigate: (String) -> Unit) {
        runBlocking {
            logOutUseCase.logOut()
            navigate(SPLASH_SCREEN)
        }
    }
}