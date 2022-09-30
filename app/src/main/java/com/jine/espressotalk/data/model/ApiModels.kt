package com.jine.espressotalk.data.model

data class PokemonApiModel(
    val height: Int,
    val weight: Int,
    val sprites: SpritesApiModel
)

data class SpritesApiModel(
    val front_default: String
)

data class PokemonSpeciesApiModel(
    val genera: List<GeneraApiModel>,
    val names: List<NameApiModel>
)

data class GeneraApiModel(
    val genus: String,
    val language: LanguageApiModel
)

data class NameApiModel(
    val name: String,
    val language: LanguageApiModel
)

data class LanguageApiModel(
    val name: String
)