package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.AssetsDataSource
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.test_utils.AssetsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AssetsRepositoryImplTest {

    private lateinit var sut: AssetsRepositoryImpl

    private val mockAssetsDataSource: AssetsDataSource.Remote = mockk()

    @Before
    fun setup() {
        sut = AssetsRepositoryImpl(
            remoteAssetsDataSource = mockAssetsDataSource
        )
    }

    @Test
    fun `when getAssetsList then call the assets data source properly`() = runTest {
        // arrange
        coEvery {
            mockAssetsDataSource.getAssetsList()
        } returns ResultWrapper.success(AssetsUtils.appAssetsList)

        // act
        sut.getAssetsList()

        // assert
        coVerifyOnce {
            mockAssetsDataSource.getAssetsList()
        }
    }

    @Test
    fun `when getAssetsList is successful then return a success result with an AppAssets list`() =
        runTest {
            // arrange
            coEvery {
                mockAssetsDataSource.getAssetsList()
            } returns ResultWrapper.success(AssetsUtils.appAssetsList)

            // act
            val result = sut.getAssetsList()

            // assert
            assert(result.isSuccess)

            assertEquals(
                ResultWrapper.success(AssetsUtils.appAssetsList),
                result
            )
        }

    @Test
    fun `when getAssetsList is a failure then return a failure result`() = runTest {
        // arrange
        coEvery {
            mockAssetsDataSource.getAssetsList()
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut.getAssetsList()

        // assert
        assert(result.isError)
    }
}
