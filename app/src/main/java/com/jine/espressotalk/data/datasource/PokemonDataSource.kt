package com.jine.espressotalk.data.datasource

import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.data.utils.RequestParser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PokemonDataSource(private val context: Context) {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }

    private val service: PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }

    suspend fun getAll(): List<PokemonModel> {
        return coroutineScope {
            (1..151).map { pokemonNumber ->
                async { getDetail(pokemonNumber) }
            }.awaitAll().sortedBy { it.number }
        }
    }

    private suspend fun getDetail(pokemonNumber: Int): PokemonModel {
        // Main pokemon info
        val pokemonApiModel = RequestParser.parse {
            service.getPokemon(pokemonNumber.toString()).execute()
        }
        // Additional pokemon info
        val pokemonSpeciesApiModel = RequestParser.parse {
            service.getPokemonSpecies(pokemonNumber.toString()).execute()
        }
        // Image
        val pokemonBitmap = context.imageLoader.execute(
            ImageRequest.Builder(context).data(pokemonApiModel.sprites.front_default)
                .allowHardware(false).build()
        ).drawable!!.toBitmap()
        // Background
        val mainColor = Palette.from(pokemonBitmap).generate().getDominantColor(0)
        val pokemonBackground = Color.argb(
            90,
            Color.red(mainColor),
            Color.green(mainColor),
            Color.blue(mainColor)
        )
        return PokemonModel(
            number = pokemonNumber,
            name = pokemonSpeciesApiModel.names.first { it.language.name == "fr" }.name,
            type = pokemonSpeciesApiModel.genera.first { it.language.name == "fr" }.genus,
            imageBitmap = pokemonBitmap,
            backgroundColor = pokemonBackground,
            height = pokemonApiModel.height,
            weight = pokemonApiModel.weight
        )
    }
}