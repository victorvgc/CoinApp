package com.example.coinapp.feature.details_page.domain.use_case

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

class GetAllExchangesUseCaseImplTest {

    private lateinit var sut: GetAllExchangesUseCaseImpl

    private val mockExchangeRepository: ExchangeRepository = mockk()

    @Before
    fun setup() {
        sut = GetAllExchangesUseCaseImpl(
            exchangeRepository = mockExchangeRepository
        )
    }

    @Test
    fun `when invoke then call the repository properly`() = runTest {
        // arrange
        coEvery {
            mockExchangeRepository.getExchangeList()
        } returns ResultWrapper.success(emptyList())

        // act
        sut()

        // assert
        coVerifyOnce {
            mockExchangeRepository.getExchangeList()
        }
    }

    @Test
    fun `when invoke is successful then return the success result`() = runTest {
        // arrange
        coEvery {
            mockExchangeRepository.getExchangeList()
        } returns ResultWrapper.success(ExchangesUtils.appExchangeList)

        // act
        val result = sut()

        // assert
        assert(result.isSuccess)

        assertEquals(
            ResultWrapper.success(ExchangesUtils.appExchangeList),
            result
        )
    }

    @Test
    fun `when invoke is an error then return the error result`() = runTest {
        // arrange
        coEvery {
            mockExchangeRepository.getExchangeList()
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut()

        // assert
        assert(result.isError)
    }
}
