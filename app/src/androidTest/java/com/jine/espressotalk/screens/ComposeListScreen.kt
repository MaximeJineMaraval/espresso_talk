package com.jine.espressotalk.screens

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performScrollToNode
import com.jine.espressotalk.internal.Pokemon
import com.jine.espressotalk.tests.TestTags

class ComposeListScreen(private val composeRule: AndroidComposeTestRule<*, *>) {

    private val pokemonList = hasTestTag(TestTags.PokemonList)

    fun scrollTo(pokemon: Pokemon): SemanticsNodeInteraction {
        return composeRule.onNode(pokemonList).performScrollToNode(hasText(pokemon.name))
    }

}