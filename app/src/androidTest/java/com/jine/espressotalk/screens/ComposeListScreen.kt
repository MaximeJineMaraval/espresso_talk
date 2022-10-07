package com.jine.espressotalk.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jine.espressotalk.R
import com.jine.espressotalk.internal.Pokemon
import com.jine.espressotalk.tests.TestTags

class ComposeListScreen(private val composeRule: AndroidComposeTestRule<*, *>) {

    private val pokemonList = hasTestTag(TestTags.PokemonList)

    fun scrollTo(pokemon: Pokemon): SemanticsNodeInteraction {
        return composeRule.onNode(pokemonList).performScrollToNode(hasText(pokemon.name))
    }

    fun enterSearchText(text: String) {
        composeRule.onNode(hasTestTag(TestTags.SearchBar)).performTextInput(text)
    }

    // todo possible de faire un équivalent côté XML ?
    fun checkIsDisplayed(pokemon: Pokemon) {
        composeRule.onAllNodes(
            hasText(pokemon.name).and(hasAnyAncestor(pokemonList))
        ).assertCountEquals(1)
    }

    // todo possible de faire un équivalent côté XML ?
    fun checkIsNotDisplayed(pokemon: Pokemon) {
        composeRule.onAllNodes(
            hasText(pokemon.name).and(hasAnyAncestor(pokemonList))
        ).assertCountEquals(0)
    }

    fun markAsFavorite(pokemon: Pokemon) {
        composeRule.onNode(hasTestTag(TestTags.getFavoriteItemIcon(pokemon.name)))
            .performClick()
    }

    fun showOnlyFavorite() {
        onView(withId(R.id.topBarFavorite))
            .perform(click())
    }

}