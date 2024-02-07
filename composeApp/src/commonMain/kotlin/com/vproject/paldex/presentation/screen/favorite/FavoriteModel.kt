package com.vproject.paldex.presentation.screen.favorite

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.paldex.repository.PalRepository
import kotlinx.coroutines.launch

class FavoriteModel(private val palRepository: PalRepository) : StateScreenModel<FavoriteUiState>(
    FavoriteUiState.Initial) {
    fun getFavoritePalList() = screenModelScope.launch {
        palRepository.getFavoritePalList().collect {favoritePalList ->
            mutableState.value = FavoriteUiState.Success(favoritePalList)
        }
    }
}