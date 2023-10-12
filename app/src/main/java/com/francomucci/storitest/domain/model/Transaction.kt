package com.francomucci.storitest.domain.model

data class Transaction(
    val title: String = "",
    val value: Float = 0f,
    val date: String = "",
    val id: String = "",
)