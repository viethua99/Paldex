package com.vproject.paldex.presentation.screen.favorite

import com.vproject.paldex.model.Pal

sealed class FavoriteUiState {
    data object Initial : FavoriteUiState()
    data object Loading : FavoriteUiState()
    data class Success(val palList: List<Pal>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}