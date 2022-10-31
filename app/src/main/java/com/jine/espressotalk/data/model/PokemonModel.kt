package com.jine.espressotalk.data.model

import android.graphics.Bitmap
import androidx.annotation.ColorInt

data class PokemonModel(
    val number: Int,
    val name: String,
    val type: String,
    val imageBitmap: Bitmap,
    @ColorInt val backgroundColor: Int,
    private val height: Int,
    private val weight: Int,
) {
    val heightAndWeight: String =
        "${String.format("%.1f", height * 0.1)} m, ${String.format("%.1f", weight * 0.1)} kg"
}