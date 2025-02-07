package com.example.coinapp.core.ui.orderbook_chart.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookData
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookEntry
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookEntryType
import java.math.BigDecimal

class OrderBookPreviewParameters : PreviewParameterProvider<OrderBookData>{
    override val values: Sequence<OrderBookData>
        get() = sequenceOf(
            OrderBookData(
                title = "BTC/USDT",
                entries = listOf(
                    OrderBookEntry(
                        price = BigDecimal("98917.20000000"),
                        size = BigDecimal("0.00056000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.21000000"),
                        size = BigDecimal("0.00100000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.22000000"),
                        size = BigDecimal("0.04000000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.23000000"),
                        size = BigDecimal("0.00032000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.26000000"),
                        size = BigDecimal("0.02981000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.18000000"),
                        size = BigDecimal("0.03646000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.13000000"),
                        size = BigDecimal("0.04767000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.12000000"),
                        size = BigDecimal("0.02612000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.09000000"),
                        size = BigDecimal("0.00881000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98820.22000000"),
                        size = BigDecimal("0.02200000"),
                        type = OrderBookEntryType.BID
                    ),
                )
            ),
            OrderBookData(
                title = "BTC/USDT",
                entries = listOf(
                    OrderBookEntry(
                        price = BigDecimal("98917.20000000"),
                        size = BigDecimal("0.00056000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.21000000"),
                        size = BigDecimal("0.00100000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.22000000"),
                        size = BigDecimal("0.04000000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.23000000"),
                        size = BigDecimal("0.00032000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.26000000"),
                        size = BigDecimal("0.02981000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.18000000"),
                        size = BigDecimal("0.03646000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.13000000"),
                        size = BigDecimal("0.04767000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.12000000"),
                        size = BigDecimal("0.02612000"),
                        type = OrderBookEntryType.BID
                    ),
                )
            ),
            OrderBookData(
                title = "BTC/USDT",
                entries = listOf(
                    OrderBookEntry(
                        price = BigDecimal("98917.20000000"),
                        size = BigDecimal("0.00056000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.21000000"),
                        size = BigDecimal("0.00100000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98917.22000000"),
                        size = BigDecimal("0.04000000"),
                        type = OrderBookEntryType.ASK
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.18000000"),
                        size = BigDecimal("0.03646000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.13000000"),
                        size = BigDecimal("0.04767000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.12000000"),
                        size = BigDecimal("0.02612000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98862.09000000"),
                        size = BigDecimal("0.00881000"),
                        type = OrderBookEntryType.BID
                    ),
                    OrderBookEntry(
                        price = BigDecimal("98820.22000000"),
                        size = BigDecimal("0.02200000"),
                        type = OrderBookEntryType.BID
                    ),
                )
            ),
        )
}