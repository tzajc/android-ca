package com.example.android_ca.feature.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.android_ca.core.base.BaseViewModel
import com.example.android_ca.feature.characters.domain.GetCharactersUseCase
import com.example.android_ca.feature.characters.domain.model.Character
import com.example.android_ca.feature.characters.presentation.model.CharacterDisplayable

class CharacterViewModel(private val getCharactersUseCase: GetCharactersUseCase) : BaseViewModel() {

    private val _characters by lazy {
        MutableLiveData<List<Character>>()
            .also { getCharacters(it) }
    }

    val characters: LiveData<List<CharacterDisplayable>> by lazy {
        _characters.map { characters ->
            characters.map { CharacterDisplayable(it) }
        }
    }

    private fun getCharacters(characterLiveData: MutableLiveData<List<Character>>) {
        setPendingState()
        getCharactersUseCase(
            params = Unit,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess { characterLiveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }
}