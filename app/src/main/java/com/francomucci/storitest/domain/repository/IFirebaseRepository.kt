package com.francomucci.storitest.domain.repository

import com.francomucci.storitest.domain.model.StoriUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface IFirebaseRepository {
    suspend fun getUser(
        onSuccessListener: (QuerySnapshot) -> Unit,
        onFailureListener: (Exception) -> Unit,
    )

    suspend fun logOut()

    suspend fun authenticate(mail: String, password: String)

    fun saveUser(
        storiUser: StoriUser,
        onSuccessListener: (DocumentReference) -> Unit,
        onFailureListener: (Exception) -> Unit,
    )

    suspend fun register(mail: String, password: String)

    fun isLogged(): Boolean

    fun getTransactionDetail(
        id: String,
        onSuccessListener: (QuerySnapshot) -> Unit,
        onFailureListener: (Exception) -> Unit,
    )
}