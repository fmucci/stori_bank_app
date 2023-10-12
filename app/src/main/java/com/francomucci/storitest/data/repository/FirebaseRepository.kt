package com.francomucci.storitest.data.repository

import com.francomucci.storitest.domain.model.StoriUser
import com.francomucci.storitest.domain.repository.IFirebaseRepository
import com.francomucci.storitest.data.services.AccountService
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val UID_KEY = "UID"
const val NAME_KEY = "name"
const val LASTNAME_KEY = "lastname"
const val USERS_QUERY_KEY = "users"

class FirebaseRepository @Inject constructor(
    private val accountService: AccountService,
    private val firestore: FirebaseFirestore,
): IFirebaseRepository {

    override suspend fun getUser(
        onSuccessListener: (QuerySnapshot) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        return withContext(Dispatchers.IO) {
            firestore.collection(USERS_QUERY_KEY)
                .whereEqualTo(UID_KEY, accountService.currentUserId)
                .get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
    }

    override suspend fun logOut() {
        accountService.logOut()
    }

    override suspend fun authenticate(mail: String, password: String) {
        accountService.authenticate(mail, password)
    }

    override fun saveUser(
        storiUser: StoriUser,
        onSuccessListener: (DocumentReference) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        val user = hashMapOf(
            NAME_KEY to storiUser.name,
            LASTNAME_KEY to storiUser.lastname,
            UID_KEY to accountService.currentUserId,
        )

        firestore.collection(USERS_QUERY_KEY)
            .add(user)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override suspend fun register(mail: String, password: String) {
        accountService.register(mail, password)
    }

    override fun isLogged() = accountService.logged

    override fun getTransactionDetail(
        id: String,
        onSuccessListener: (QuerySnapshot) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        firestore.collection(USERS_QUERY_KEY)
            .whereEqualTo(UID_KEY, accountService.currentUserId)
            .get()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }
}