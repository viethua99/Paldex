package com.vproject.paldex.presentation.screen.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.paldex.repository.PalRepository
import kotlinx.coroutines.launch

class HomeModel(private val palRepository: PalRepository) : StateScreenModel<HomeUiState>(HomeUiState.Initial) {
    fun getPalList() = screenModelScope.launch {
        palRepository.getPalList(1)
            .onSuccess {
                mutableState.value = HomeUiState.Success(it)
            }
            .onFailure {
                mutableState.value = HomeUiState.Error(it.message ?: "Unknown error")
            }
    }
}