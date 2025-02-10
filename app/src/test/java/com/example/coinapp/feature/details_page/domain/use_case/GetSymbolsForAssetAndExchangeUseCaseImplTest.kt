package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.SymbolRepository
import com.example.coinapp.test_utils.AssetsUtils
import com.example.coinapp.test_utils.ExchangesUtils
import com.example.coinapp.test_utils.SymbolsUtils
import com.example.coinapp.test_utils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetSymbolsForAssetAndExchangeUseCaseImplTest {

    private lateinit var sut: GetSymbolsForAssetAndExchangeUseCaseImpl

    private val mockSymbolRepository: SymbolRepository = mockk()

    @Before
    fun setup() {
        sut = GetSymbolsForAssetAndExchangeUseCaseImpl(
            symbolRepository = mockSymbolRepository
        )
    }

    @Test
    fun `when invoke then call the repository properly`() = runTest {
        // arrange
        coEvery {
            mockSymbolRepository.getSymbolsListFromAssetAndExchangeIds(any(), any())
        } returns ResultWrapper.success(emptyList())

        // act
        sut(
            asset = AssetsUtils.appAssetsList.first(),
            exchange = ExchangesUtils.appExchangeList.first()
        )

        // assert
        coVerifyOnce {
            mockSymbolRepository.getSymbolsListFromAssetAndExchangeIds(
                assetId = AssetsUtils.appAssetsList.first().id,
                exchangeId = ExchangesUtils.appExchangeList.first().id
            )
        }
    }

    @Test
    fun `when invoke is successful then return the success result`() = runTest {
        // arrange
        coEvery {
            mockSymbolRepository.getSymbolsListFromAssetAndExchangeIds(any(), any())
        } returns ResultWrapper.success(SymbolsUtils.appSymbolList)

        // act
        val result = sut(
            asset = AssetsUtils.appAssetsList.first(),
            exchange = ExchangesUtils.appExchangeList.first()
        )

        // assert
        assert(result.isSuccess)

        assertEquals(
            ResultWrapper.success(SymbolsUtils.appSymbolList),
            result
        )
    }

    @Test
    fun `when invoke is an error then return the error result`() = runTest {
        // arrange
        coEvery {
            mockSymbolRepository.getSymbolsListFromAssetAndExchangeIds(any(), any())
        } returns ResultWrapper.error(Exception())

        // act
        val result = sut(
            asset = AssetsUtils.appAssetsList.first(),
            exchange = ExchangesUtils.appExchangeList.first()
        )

        // assert
        assert(result.isError)
    }
}
