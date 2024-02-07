package com.vproject.paldex.presentation.screen.home

import com.vproject.paldex.model.Pal

sealed class HomeUiState {
    data object Initial : HomeUiState()
    data object Loading : HomeUiState()
    data class Success(val palList: List<Pal>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}