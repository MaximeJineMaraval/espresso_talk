package com.jine.espressotalk.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val PokemonRedDark = Color(0xffcc0000)
private val PokemonBlue = Color(0xff3b4cca)
private val PokemonBlueDark = Color(0xff2c3998)
private val PokemonYellowDark = Color(0xffb3a125)

private val PokemonThemeColors = lightColors(
    primary = PokemonBlue,
    primaryVariant = PokemonBlueDark,
    onPrimary = Color.White,
    secondary = PokemonBlue,
    secondaryVariant = PokemonBlue,
    onSecondary = Color.White,
)

@Composable
fun PokemonComposeTheme(content: @Composable () -> Unit) =
    MaterialTheme(
        colors = PokemonThemeColors,
        shapes = MaterialTheme.shapes.copy(large = RoundedCornerShape(8.dp)),
        content = content
    )