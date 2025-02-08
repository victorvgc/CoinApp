package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.OrderBookRepository
import com.example.coinapp.test_utils.OrderBookUtils
import com.example.coinapp.test_utils.SymbolsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetOrderBookForSymbolUseCaseImplTest {

    private lateinit var sut: GetOrderBookForSymbolUseCaseImpl

    private val mockOrderBookRepository: OrderBookRepository = mockk()

    @Before
    fun setup() {
        sut = GetOrderBookForSymbolUseCaseImpl(
            orderBookRepository = mockOrderBookRepository
        )
    }

    @Test
    fun `when invoke then call the repository properly`() = runTest {
        // arrange
        coEvery {
            mockOrderBookRepository.getOrderBookFromSymbolId(any())
        } returns ResultWrapper.success(OrderBookUtils.appOrderBookData)

        // act
        sut(SymbolsUtils.appSymbolList.first())

        // assert
        coVerifyOnce {
            mockOrderBookRepository.getOrderBookFromSymbolId(
                symbolId = SymbolsUtils.appSymbolList.first().id
            )
        }
    }

    @Test
    fun `when invoke is successful then return the success result`() = runTest {
        // arrange
        coEvery {
            mockOrderBookRepository.getOrderBookFromSymbolId(any())
        } returns ResultWrapper.success(OrderBookUtils.appOrderBookData)

        // act
        val result = sut(SymbolsUtils.appSymbolList.first())

        // assert
        assert(result.isSuccess)

        assertEquals(
            ResultWrapper.success(OrderBookUtils.appOrderBookData),
            result
        )
    }

    @Test
    fun `when invoke is an error then return the error result`() = runTest {
        // arrange
        coEvery {
            mockOrderBookRepository.getOrderBookFromSymbolId(any())
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut(SymbolsUtils.appSymbolList.first())

        // assert
        assert(result.isError)
    }
}
