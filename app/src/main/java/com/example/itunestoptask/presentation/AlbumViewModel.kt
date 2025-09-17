package com.example.itunestoptask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunestoptask.domain.Album
import com.example.itunestoptask.domain.AlbumRepository
import com.example.itunestoptask.network.onFailure
import com.example.itunestoptask.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel
@Inject
constructor(
    private val albumRepository: AlbumRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AlbumUiState())
    private val _uiActions = MutableSharedFlow<UiAction>()

    val uiState: StateFlow<AlbumUiState> = _uiState.asStateFlow()
    val uiActions = _uiActions.asSharedFlow()

    init {
        loadImages()
    }

    fun findAlbumFromId(id: String): Album = _uiState.value.albumList.first { it.id == id }

    fun getAlbumIndex(id: String): Int = _uiState.value.albumList.indexOfFirst { it.id == id }

    fun loadImages() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    albumState = AlbumState.LOADING
                )
            }

            val albums = albumRepository.getTopAlbums()

            albums
                .onSuccess { albumList ->
                    _uiState.update {
                        it.copy(
                            albumList = albumList,
                            albumState = if (albumList.isEmpty()) {
                                AlbumState.EMPTY
                            } else {
                                AlbumState.FINISHED
                            }
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            albumState = AlbumState.ERROR
                        )
                    }
                    _uiActions.emit(UiAction.Error)
                }
        }
    }

    fun reset() {
        _uiState.update {
            it.copy(
                albumState = AlbumState.LOADING,
                albumList = emptyList()
            )
        }
    }

    data class AlbumUiState(
        val albumList: List<Album> = emptyList(),
        val albumState: AlbumState = AlbumState.LOADING
    )

    enum class AlbumState() {
        LOADING,
        ERROR,
        EMPTY,
        FINISHED
    }

    sealed class UiAction {
        data object Error : UiAction()
    }
}
