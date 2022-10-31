package com.jine.espressotalk.ui.trainercreator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainerCreatorViewModel : ViewModel() {

    private var _trainerName: MutableLiveData<String> = MutableLiveData("")
    val trainerName: LiveData<String>
        get() = _trainerName

    private var _pokemonName: MutableLiveData<String> = MutableLiveData("")
    val pokemonName: LiveData<String>
        get() = _pokemonName

    private var _isCreateButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isCreateButtonEnabled: LiveData<Boolean>
        get() = _isCreateButtonEnabled

    fun updateTrainerName(string: String) {
        _trainerName.value = string
        updateCreateButtonStatus()
    }

    fun updatePokemonName(string: String) {
        _pokemonName.value = string
        updateCreateButtonStatus()
    }

    fun clear() {
        _trainerName.value = ""
        _pokemonName.value = ""
    }

    private fun updateCreateButtonStatus() {
        _isCreateButtonEnabled.value =
            trainerName.value.isNullOrBlank().not() && pokemonName.value.isNullOrBlank().not()
    }
}