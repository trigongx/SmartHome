package com.example.smart_home.domain.models

data class Note(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val isDone:Boolean = false
)
