package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import com.francomucci.storitest.domain.model.StoriUser
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {
    suspend fun saveUser(
        storiUser: StoriUser,
        onSuccessListener: (DocumentReference) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        repository.saveUser(storiUser, onSuccessListener, onFailureListener)
    }
}