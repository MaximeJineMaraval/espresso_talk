package com.jine.espressotalk.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val _screenState: MutableLiveData<PokemonListState> = MutableLiveData()
    val screenState: LiveData<PokemonListState> = _screenState

    private var rawList: List<PokemonModel> = emptyList()
    private var finalList: List<PokemonModel> = emptyList()
        set(value) {
            field = value
            _screenState.postValue(PokemonListState.Success(value))
        }
    private var currentSearchText: String = ""
    private var showOnlyFavorite: Boolean = false

    init {
        _screenState.postValue(PokemonListState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val pokemons = PokemonRepository().getAll()
            rawList = pokemons
            finalList = pokemons
        }
    }

    fun performSearch(text: String) {
        currentSearchText = text
        updateLists()
    }

    fun makeAsFavorite(pokemonNumber: Int) {
        rawList = rawList.apply {
            find { it.number == pokemonNumber }?.toggleFavorite()
        }
        updateLists()
    }

    fun showOnlyFavorite(show: Boolean) {
        showOnlyFavorite = show
        updateLists()
    }

    fun resetFilters() {
        currentSearchText = ""
        showOnlyFavorite = false
        updateLists()
    }

    private fun updateLists() {
        finalList = rawList
            // Search
            .filter { pokemon ->
                pokemon.name.contains(currentSearchText, ignoreCase = true)
                        || pokemon.type.contains(currentSearchText, ignoreCase = true)
                // Favorite
            }.filter {
                if (showOnlyFavorite) {
                    it.isFavorite()
                } else {
                    true
                }
            }
    }
}

sealed class PokemonListState {
    object Loading : PokemonListState()
    data class Success(val pokemons: List<PokemonModel>) : PokemonListState()
}