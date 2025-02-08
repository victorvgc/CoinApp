package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.OrderBookDataSource
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.test_utils.OrderBookUtils
import com.example.coinapp.test_utils.SymbolsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OrderBookRepositoryImplTest {

    private lateinit var sut: OrderBookRepositoryImpl

    private val mockOderBookDataSource: OrderBookDataSource.Remote = mockk()

    @Before
    fun setup() {
        sut = OrderBookRepositoryImpl(
            orderBookDataSource = mockOderBookDataSource
        )
    }

    @Test
    fun `when getOrderBookFromSymbolId then call the assets data source properly`() = runTest {
        // arrange
        coEvery {
            mockOderBookDataSource.getOrderBookFromSymbolId(any())
        } returns ResultWrapper.success(OrderBookUtils.appOrderBookData)

        // act
        sut.getOrderBookFromSymbolId(
            SymbolsUtils.ID
        )

        // assert
        coVerifyOnce {
            mockOderBookDataSource.getOrderBookFromSymbolId(
                SymbolsUtils.ID
            )
        }
    }

    @Test
    fun `when getOrderBookFromSymbolId is successful then return a success result with an AppOrderBookData`() =
        runTest {
            // arrange
            coEvery {
                mockOderBookDataSource.getOrderBookFromSymbolId(any())
            } returns ResultWrapper.success(OrderBookUtils.appOrderBookData)

            // act
            val result = sut.getOrderBookFromSymbolId(
                SymbolsUtils.ID
            )

            // assert
            assert(result.isSuccess)

            assertEquals(
                ResultWrapper.success(OrderBookUtils.appOrderBookData),
                result
            )
        }

    @Test
    fun `when getOrderBookFromSymbolId is a failure then return a failure result`() = runTest {
        // arrange
        coEvery {
            mockOderBookDataSource.getOrderBookFromSymbolId(any())
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut.getOrderBookFromSymbolId(
            SymbolsUtils.ID
        )

        // assert
        assert(result.isError)
    }
}
