package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.ExchangeDataSource
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.ExchangeRepository
import com.example.coinapp.test_utils.ExchangesUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ExchangeRepositoryImplTest {

    private lateinit var sut: ExchangeRepository

    private val mockExchangeDataSource: ExchangeDataSource.Remote = mockk()

    @Before
    fun setup() {
        sut = ExchangeRepositoryImpl(
            exchangeDataSource = mockExchangeDataSource
        )
    }

    @Test
    fun `when getExchangeList then call the assets data source properly`() = runTest {
        // arrange
        coEvery {
            mockExchangeDataSource.getExchangesList()
        } returns ResultWrapper.success(ExchangesUtils.appExchangeList)

        // act
        sut.getExchangeList()

        // assert
        coVerifyOnce {
            mockExchangeDataSource.getExchangesList()
        }
    }

    @Test
    fun `when getExchangeList is successful then return a success result with an AppExchange list`() =
        runTest {
            // arrange
            coEvery {
                mockExchangeDataSource.getExchangesList()
            } returns ResultWrapper.success(ExchangesUtils.appExchangeList)

            // act
            val result = sut.getExchangeList()

            // assert
            assert(result.isSuccess)

            assertEquals(
                ResultWrapper.success(ExchangesUtils.appExchangeList),
                result
            )
        }

    @Test
    fun `when getExchangeList is a failure then return a failure result`() = runTest {
        // arrange
        coEvery {
            mockExchangeDataSource.getExchangesList()
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut.getExchangeList()

        // assert
        assert(result.isError)
    }
}
