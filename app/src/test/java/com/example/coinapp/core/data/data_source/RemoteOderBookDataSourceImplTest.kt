package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.test_utils.OrderBookUtils
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

class RemoteOderBookDataSourceImplTest {

    private lateinit var sut: RemoteOrderBookDataSourceImpl

    private val mockCoinAppService: CoinAppService = mockk()

    private val mockResponseBody: ResponseBody = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = RemoteOrderBookDataSourceImpl(
            coinAppService = mockCoinAppService
        )
    }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds then call service properly`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.getOrderBook(any(), any())
        } returns Response.success(OrderBookUtils.remoteOrderBook)

        // act
        sut.getOrderBookFromSymbolId(SymbolsUtils.ID)

        // assert
        coVerifyOnce {
            mockCoinAppService.getOrderBook(SymbolsUtils.ID)
        }
    }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds successfully then return an AppOrderBookData as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.getOrderBook(any(), any())
            } returns Response.success(OrderBookUtils.remoteOrderBook)

            // act
            val result = sut.getOrderBookFromSymbolId(SymbolsUtils.ID)

            // assert

            assertEquals(
                ResultWrapper.success(OrderBookUtils.appOrderBookData),
                result
            )
        }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds has a request error then return a failure as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.getOrderBook(any(), any())
            } returns Response.error(500, mockResponseBody)

            // act
            val result = sut.getOrderBookFromSymbolId(SymbolsUtils.ID)

            // assert
            assert(result.isError)
        }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds has a network error then return a failure as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.getOrderBook(any(), any())
            } throws Exception()

            // act
            val result = sut.getOrderBookFromSymbolId(SymbolsUtils.ID)

            // assert
            assert(result.isError)
        }
}
