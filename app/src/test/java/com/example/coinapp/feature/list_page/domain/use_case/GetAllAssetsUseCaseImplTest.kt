package com.example.coinapp.feature.list_page.domain.use_case

import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.AssetsRepository
import com.example.coinapp.test_utils.AssetsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllAssetsUseCaseImplTest {

    private lateinit var sut: GetAllAssetsUseCaseImpl

    private val mockAssetsRepository: AssetsRepository = mockk()

    @Before
    fun setup() {
        sut = GetAllAssetsUseCaseImpl(
            assetsRepository = mockAssetsRepository
        )
    }

    @Test
    fun `when invoke then call the repository properly`() = runTest {
        // arrange
        coEvery {
            mockAssetsRepository.getAssetsList()
        } returns ResultWrapper.success(emptyList())

        // act
        sut()

        // assert
        coVerifyOnce {
            mockAssetsRepository.getAssetsList()
        }
    }

    @Test
    fun `when invoke is successful then return the success result`() = runTest {
        // arrange
        coEvery {
            mockAssetsRepository.getAssetsList()
        } returns ResultWrapper.success(AssetsUtils.appAssetsList)

        // act
        val result = sut()

        // assert
        assert(result.isSuccess)

        assertEquals(
            ResultWrapper.success(AssetsUtils.appAssetsList),
            result
        )
    }

    @Test
    fun `when invoke is an error then return the error result`() = runTest {
        // arrange
        coEvery {
            mockAssetsRepository.getAssetsList()
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut()

        // assert
        assert(result.isError)
    }
}
