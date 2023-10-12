package com.francomucci.storitest.domain.usecases

import com.francomucci.storitest.data.repository.FirebaseRepository
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class GetTransactionDetailUseCase @Inject constructor(
    val repository: FirebaseRepository,
) {

    suspend fun getTransactionDetail(
        id: String,
        onSuccessListener: (QuerySnapshot) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        repository.getTransactionDetail(id, onSuccessListener, onFailureListener)
    }
}