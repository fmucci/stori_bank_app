package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {
    suspend fun authenticate(mail: String, password: String) {
        repository.authenticate(mail, password)
    }
}