package com.jine.espressotalk.data.repository

import com.jine.espressotalk.data.datasource.PokemonDataSource
import com.jine.espressotalk.data.model.PokemonModel

class PokemonRepository {

    private val dataSource = PokemonDataSource()

    suspend fun getAll(): List<PokemonModel> {
        return dataSource.getAll()
    }
}