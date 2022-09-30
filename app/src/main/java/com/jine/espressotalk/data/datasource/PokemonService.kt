package com.jine.espressotalk.data.datasource

import com.jine.espressotalk.data.model.PokemonApiModel
import com.jine.espressotalk.data.model.PokemonSpeciesApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{number}")
    fun getPokemon(@Path("number") number: String): Call<PokemonApiModel>

    @GET("pokemon-species/{number}")
    fun getPokemonSpecies(@Path("number") number: String): Call<PokemonSpeciesApiModel>
}