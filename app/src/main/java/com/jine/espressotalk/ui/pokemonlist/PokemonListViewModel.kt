package com.jine.espressotalk.ui.pokemonlist

import android.app.Application
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.data.repository.PokemonRepository
import com.jine.espressotalk.tests.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel(application: Application) : AndroidViewModel(application) {

    private val _screenState: MutableLiveData<PokemonListState> = MutableLiveData()
    val screenState: LiveData<PokemonListState> = _screenState

    init {
        EspressoIdlingResource.increment()
        _screenState.postValue(PokemonListState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val pokemons = PokemonRepository(application).getAll()
            _screenState.postValue(PokemonListState.Success(pokemons.toMutableStateList()))
            EspressoIdlingResource.decrement()
        }
    }

}

sealed class PokemonListState {
    object Loading : PokemonListState()
    data class Success(val pokemons: List<PokemonModel>) : PokemonListState()
}