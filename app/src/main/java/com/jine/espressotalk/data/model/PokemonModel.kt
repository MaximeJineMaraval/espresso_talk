package com.jine.espressotalk.data.model

data class PokemonModel(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val type: String,
    private val height: Int,
    private val weight: Int,
) {
    val heightAndWeight: String =
        "${String.format("%.1f", height * 0.1)} m, ${String.format("%.1f", weight * 0.1)} kg"
}