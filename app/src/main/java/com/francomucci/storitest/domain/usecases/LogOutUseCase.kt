package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {
    suspend fun logOut() {
        repository.logOut()
    }
}