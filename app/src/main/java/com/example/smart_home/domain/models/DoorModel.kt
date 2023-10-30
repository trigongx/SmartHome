package com.example.smart_home.domain.models

data class DoorModel(
    val `data`: List<Data>,
    val success: Boolean
) {
    data class Data(
        val favorites: Boolean,
        val id: Int,
        val name: String,
        val room: String,
        val snapshot: String? = null
    )
}
