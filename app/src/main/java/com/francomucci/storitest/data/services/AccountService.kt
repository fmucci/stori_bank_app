package com.francomucci.storitest.data.services

interface AccountService {
    val currentUserId: String
    val logged: Boolean

    suspend fun authenticate(email: String, password: String)

    suspend fun register(email: String, password: String)

    suspend fun logOut()
}