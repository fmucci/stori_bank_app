package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {

    suspend fun getUser(
        onSuccessListener: (QuerySnapshot) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        repository.getUser(onSuccessListener, onFailureListener)
    }
}