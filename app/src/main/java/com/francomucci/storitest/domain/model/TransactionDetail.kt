package com.francomucci.storitest.domain.model

data class TransactionDetail(
    val title: String = "",
    val value: Float = 0f,
    val date: String = "",
    val id: String = "",
)

data class TransactionUser(
    val transactions: List<TransactionDetail> = arrayListOf(),
)