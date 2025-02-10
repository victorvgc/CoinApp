package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.SymbolDataSource
import com.example.coinapp.core.domain.model.ResultWrapper
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

class SymbolRepositoryImplTest {

    private lateinit var sut: SymbolRepositoryImpl

    private val mockSymbolDataSource: SymbolDataSource.Remote = mockk()

    @Before
    fun setup() {
        sut = SymbolRepositoryImpl(
            symbolDataSource = mockSymbolDataSource
        )
    }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds then call the assets data source properly`() =
        runTest {
            // arrange
            coEvery {
                mockSymbolDataSource.getSymbolsFromAssetAndExchangeIds(any(), any())
            } returns ResultWrapper.success(SymbolsUtils.appSymbolList)

            // act
            sut.getSymbolsListFromAssetAndExchangeIds(
                assetId = AssetsUtils.ID,
                exchangeId = ExchangesUtils.ID
            )

            // assert
            coVerifyOnce {
                mockSymbolDataSource.getSymbolsFromAssetAndExchangeIds(
                    assetId = AssetsUtils.ID,
                    exchangeId = ExchangesUtils.ID
                )
            }
        }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds is successful then return a success result with an AppSymbols list`() =
        runTest {
            // arrange
            coEvery {
                mockSymbolDataSource.getSymbolsFromAssetAndExchangeIds(any(), any())
            } returns ResultWrapper.success(SymbolsUtils.appSymbolList)

            // act
            val result = sut.getSymbolsListFromAssetAndExchangeIds(
                assetId = AssetsUtils.ID,
                exchangeId = ExchangesUtils.ID
            )

            // assert
            assert(result.isSuccess)

            assertEquals(
                ResultWrapper.success(SymbolsUtils.appSymbolList),
                result
            )
        }

    @Test
    fun `when getSymbolsFromAssetAndExchangeIds is a failure then return a failure result`() =
        runTest {
            // arrange
            coEvery {
                mockSymbolDataSource.getSymbolsFromAssetAndExchangeIds(any(), any())
            } returns ResultWrapper.error(Exception())

            // act
            val result = sut.getSymbolsListFromAssetAndExchangeIds(
                assetId = AssetsUtils.ID,
                exchangeId = ExchangesUtils.ID
            )

            // assert
            assert(result.isError)
        }
}
