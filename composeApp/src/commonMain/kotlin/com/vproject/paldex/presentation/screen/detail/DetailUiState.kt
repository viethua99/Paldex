package com.vproject.paldex.presentation.screen.detail

import com.vproject.paldex.model.PalInfo

sealed class DetailUiState {
    data object Initial : DetailUiState()
    data object Loading : DetailUiState()
    data class Success(val palInfo: PalInfo) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}