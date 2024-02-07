package com.vproject.paldex.presentation.screen.detail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.paldex.repository.PalRepository
import kotlinx.coroutines.launch

class DetailModel(private val palRepository: PalRepository) : StateScreenModel<DetailUiState>(DetailUiState.Initial) {
    fun getPalDetail(name: String) = screenModelScope.launch {
        palRepository.getPalDetail(name)
            .onSuccess {
                mutableState.value = DetailUiState.Success(it)
            }
            .onFailure {
                mutableState.value = DetailUiState.Error(it.message ?: "Unknown error")
            }
    }

    fun setPalInfoFavorite(name: String, isFavorite: Boolean) = screenModelScope.launch {
        palRepository.setPalInfoFavorite(name, isFavorite)
        getPalDetail(name)
    }
}