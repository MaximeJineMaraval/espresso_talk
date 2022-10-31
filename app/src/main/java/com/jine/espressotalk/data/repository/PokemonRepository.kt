package com.jine.espressotalk.data.repository

import android.content.Context
import com.jine.espressotalk.data.datasource.PokemonDataSource
import com.jine.espressotalk.data.model.PokemonModel

class PokemonRepository(context: Context) {

    private val dataSource = PokemonDataSource(context)

    suspend fun getAll(): List<PokemonModel> {
        return dataSource.getAll()
    }
}