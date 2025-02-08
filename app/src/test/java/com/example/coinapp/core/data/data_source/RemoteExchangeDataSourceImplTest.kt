package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.data.model.remote.RemoteExchange
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.test_utils.ExchangesUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteExchangeDataSourceImplTest {

    private lateinit var sut: RemoteExchangeDataSourceImpl

    private val mockCoinAppService: CoinAppService = mockk()

    private val mockResponseBody: ResponseBody = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = RemoteExchangeDataSourceImpl(
            coinAppService = mockCoinAppService
        )
    }

    @Test
    fun `when getExchangesList then call service properly`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllExchanges()
        } returns Response.success(emptyList())

        // act
        sut.getExchangesList()

        // assert
        coVerifyOnce {
            mockCoinAppService.listAllExchanges()
        }
    }

    @Test
    fun `when getExchangesList successfully then return a list of AppExchange as result`() =
        runTest {
            // arrange
            coEvery {
                mockCoinAppService.listAllExchanges()
            } returns Response.success(ExchangesUtils.remoteExchangeList)

            // act
            val result = sut.getExchangesList()

            // assert

            assertEquals(
                ResultWrapper.success(ExchangesUtils.appExchangeList),
                result
            )
        }

    @Test
    fun `when getExchangesList has a request error then return a failure as result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllExchanges()
        } returns Response.error(500, mockResponseBody)

        // act
        val result = sut.getExchangesList()

        // assert
        assert(result.isError)
    }

    @Test
    fun `when getExchangesList has a network error then return a failure as result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllExchanges()
        } throws Exception()

        // act
        val result = sut.getExchangesList()

        // assert
        assert(result.isError)
    }

    @Test
    fun `when getExchangesList has response with empty name then exclude from result`() = runTest {
        // arrange
        coEvery {
            mockCoinAppService.listAllExchanges()
        } returns Response.success(
            buildList {
                addAll(ExchangesUtils.remoteExchangeList)
                add(
                    RemoteExchange(
                        id = "",
                        name = null,
                        rank = 3,
                    )
                )
            }
        )

        // act
        val result = sut.getExchangesList()

        // assert

        assertEquals(
            ResultWrapper.success(ExchangesUtils.appExchangeList),
            result
        )
    }
}
