package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {
    suspend fun register(mail: String, password: String) {
        repository.register(mail, password)
    }
}