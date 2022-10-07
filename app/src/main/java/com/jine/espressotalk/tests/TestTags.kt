package com.jine.espressotalk.tests

object TestTags {
    const val PokemonList = "PokemonList"
    const val SearchBar = "SearchBar"
    fun getFavoriteItemIcon(pokemonName: String): String {
        return "FavoriteItemIcon-$pokemonName"
    }
}