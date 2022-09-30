package com.jine.espressotalk.ui

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

    init {
        _screenState.postValue(PokemonListState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.postValue(PokemonListState.Success(PokemonRepository().getAll()))
        }
    }
}

sealed class PokemonListState {
    object Loading : PokemonListState()
    data class Success(val pokemons: List<PokemonModel>) : PokemonListState()
}