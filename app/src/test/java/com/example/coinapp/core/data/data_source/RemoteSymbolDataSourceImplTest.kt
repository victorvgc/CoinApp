package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.test_utils.AssetsUtils
import com.example.coinapp.test_utils.ExchangesUtils
import com.example.coinapp.test_utils.SymbolsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteSymbolDataSourceImplTest {

    private lateinit var sut: RemoteSymbolDataSourceImpl

    private val mockCoinAppService: CoinAppService = mockk()

    private val mockResponseBody: ResponseBody = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = RemoteSymbolDataSourceImpl(
            coinAppService = mockCoinAppService
        )
    }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds then call service properly`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listSymbolsByAssetAndExchange(any(), any(), any())
        } returns Response.success(emptyList())

        // act
        sut.getSymbolsFromAssetAndExchangeIds(
            AssetsUtils.ID,
            ExchangesUtils.ID
        )

        // assert
        coVerifyOnce {
            mockCoinAppService.listSymbolsByAssetAndExchange(
                baseAssetId = AssetsUtils.ID,
                exchangeId = ExchangesUtils.ID,
                symbolId = "${ExchangesUtils.ID}_SPOT_${AssetsUtils.ID}_USD",
            )
        }
    }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds successfully then return a list of AppSymbols as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.listSymbolsByAssetAndExchange(any(), any(), any())
            } returns Response.success(SymbolsUtils.remoteSymbolList)

            // act
            val result = sut.getSymbolsFromAssetAndExchangeIds(
                AssetsUtils.ID,
                ExchangesUtils.ID
            )

            // assert

            assertEquals(
                ResultWrapper.success(SymbolsUtils.appSymbolList),
                result
            )
        }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds has a request error then return a failure as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.listSymbolsByAssetAndExchange(any(), any(), any())
            } returns Response.error(500, mockResponseBody)

            // act
            val result = sut.getSymbolsFromAssetAndExchangeIds(
                AssetsUtils.ID,
                ExchangesUtils.ID
            )

            // assert
            assert(result.isError)
        }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds has a network error then return a failure as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.listSymbolsByAssetAndExchange(any(), any(), any())
            } throws Exception()

            // act
            val result = sut.getSymbolsFromAssetAndExchangeIds(
                AssetsUtils.ID,
                ExchangesUtils.ID
            )

            // assert
            assert(result.isError)
        }
}
