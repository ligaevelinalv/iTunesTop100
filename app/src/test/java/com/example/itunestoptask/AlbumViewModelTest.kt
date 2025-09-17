package com.example.itunestoptask

import app.cash.turbine.test
import com.example.itunestoptask.domain.AlbumRepository
import com.example.itunestoptask.network.NetworkResult
import com.example.itunestoptask.presentation.AlbumViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumViewModelTest {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var mockAlbumRepository: AlbumRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockAlbumRepository = mockk()
        viewModel = AlbumViewModel(mockAlbumRepository)
    }

    @Test
    fun `successful empty list response sets state to EMPTY`() = runTest {
        coEvery { mockAlbumRepository.getTopAlbums() } returns NetworkResult.Success(emptyList())

        viewModel.uiState.test {

            viewModel.loadImages()

            assertEquals(awaitItem().albumState, AlbumViewModel.AlbumState.LOADING)

            assertEquals(awaitItem().albumState, AlbumViewModel.AlbumState.EMPTY)

            cancelAndConsumeRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.reset()
    }
}
