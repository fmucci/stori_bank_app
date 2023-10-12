package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import javax.inject.Inject

class GetLoggedStatusUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {
    fun isLogged(): Boolean {
        return repository.isLogged()
    }
}