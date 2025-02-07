package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.data.model.remote.RemoteAsset
import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.test_utils.AssetsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.math.BigDecimal

class RemoteAssetsDataSourceImplTest {

    private lateinit var sut: RemoteAssetsDataSourceImpl

    private val mockCoinAppService: CoinAppService = mockk()

    private val mockResponseBody: ResponseBody = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = RemoteAssetsDataSourceImpl(
            coinAppService = mockCoinAppService
        )
    }

    @Test
    fun `when getAssetsList then call service properly`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllAssets()
        } returns Response.success(emptyList())

        // act
        sut.getAssetsList()

        // assert
        coVerifyOnce {
            mockCoinAppService.listAllAssets()
        }
    }

    @Test
    fun `when getAssetsList successfully then return a list of AppAssets as result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllAssets()
        } returns Response.success(AssetsUtils.remoteAssetsList)

        // act
        val result = sut.getAssetsList()

        // assert

        assertEquals(
            ResultWrapper.success(AssetsUtils.appAssetsList),
            result
        )
    }

    @Test
    fun `when getAssetsList has a request error then return a failure as result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllAssets()
        } returns Response.error(500, mockResponseBody)

        // act
        val result = sut.getAssetsList()

        // assert
        assert(result.isError)
    }

    @Test
    fun `when getAssetsList has a network error then return a failure as result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllAssets()
        } throws Exception()

        // act
        val result = sut.getAssetsList()

        // assert
        assert(result.isError)
    }

    @Test
    fun `when getAssetsList has response with empty name then exclude from result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllAssets()
        } returns Response.success(
            buildList {
                addAll(AssetsUtils.remoteAssetsList)
                add(
                    RemoteAsset(
                        id = "",
                        name = null,
                        isCrypto = 1,
                        dailyVolume = 0.0,
                        icon = null,
                    )
                )
            }
        )

        // act
        val result = sut.getAssetsList()

        // assert

        assertEquals(
            ResultWrapper.success(AssetsUtils.appAssetsList),
            result
        )
    }

    @Test
    fun `when getAssetsList is successful then remove '-' chars from icon ids`() = runTest {
        // arrange
        val response = buildList {
            addAll(AssetsUtils.remoteAssetsList)
            add(
                RemoteAsset(
                    id = "tt2",
                    name = "test2",
                    isCrypto = 1,
                    dailyVolume = 0.0,
                    icon = "a-b-c",
                )
            )
        }

        coEvery {
            mockCoinAppService.listAllAssets()
        } returns Response.success(response)

        // act
        val result = sut.getAssetsList()

        // assert
        val expected = buildList {
            addAll(AssetsUtils.appAssetsList)
            add(
                AppAsset(
                    id = "tt2",
                    name = "test2",
                    isCrypto = true,
                    dailyVolume = BigDecimal.ZERO,
                    iconId = "abc",
                )
            )
        }
        assertEquals(
            ResultWrapper.success(expected),
            result
        )
    }
}
