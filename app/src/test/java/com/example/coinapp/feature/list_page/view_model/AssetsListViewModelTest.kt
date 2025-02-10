package com.example.coinapp.feature.list_page.view_model

import app.cash.turbine.test
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.ui.model.UiState
import com.example.coinapp.feature.list_page.domain.use_case.GetAllAssetsUseCase
import com.example.coinapp.test_utils.AssetsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AssetsListViewModelTest {

    private lateinit var sut: AssetsListViewModel

    private val mockGetAllAssetsUseCase: GetAllAssetsUseCase = mockk()

    @Test
    fun `when init then get all assets from use case`() = runTest {
        // arrange
        coEvery {
            mockGetAllAssetsUseCase.invoke()
        } returns ResultWrapper.success(AssetsUtils.appAssetsList)

        // act
        sut = AssetsListViewModel(
            getAllAssetsUseCase = mockGetAllAssetsUseCase
        )

        // assert
        coVerifyOnce {
            mockGetAllAssetsUseCase.invoke()
        }
    }

    @Test
    fun `when getAssetsList is successful then emit a new ui state`() = runTest {
        // arrange
        coEvery {
            mockGetAllAssetsUseCase.invoke()
        } returns ResultWrapper.success(AssetsUtils.appAssetsList)

        // act
        sut = AssetsListViewModel(
            getAllAssetsUseCase = mockGetAllAssetsUseCase
        )

        // assert
        sut.uiState.test {
            val result = awaitItem()

            assert(result is UiState.Success)
        }
    }
}
