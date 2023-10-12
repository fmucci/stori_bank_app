package com.francomucci.storitest.domain.model

data class StoriUser(
    val name: String = "",
    val balance: Float = 0f,
    val lastname: String = "",
    val transactions: List<Transaction> = arrayListOf(),
)