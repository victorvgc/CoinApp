package com.example.coinapp.feature.details_page.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.coinapp.R
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookData
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookEntry
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookEntryType
import com.example.coinapp.core.ui.orderbook_chart.ui.OrderBookComponent
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem
import com.example.coinapp.feature.details_page.ui.model.UiOrderBookData
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem
import com.example.coinapp.feature.details_page.ui.preview.AssetOrderBookPreviewParameters

@Composable
fun AssetOrderBookComponent(
    selectedExchange: UiExchangeItem,
    selectedSymbol: UiSymbolItem,
    orderBookData: UiOrderBookData,
    onClickBackToExchange: () -> Unit,
    onClickBackToSymbol: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        ListItem(
            overlineContent = {
                Text(
                    text = selectedExchange.id
                )
            },
            headlineContent = {
                Text(
                    text = selectedExchange.name
                )
            },
            trailingContent = {
                IconButton(
                    onClick = onClickBackToExchange
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = stringResource(R.string.select_arrow).format(
                            selectedExchange.name
                        ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .padding(start = Dimens.Spacing.extraSmall)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
        ) {
            Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    headlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    overlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                overlineContent = {
                    Text(
                        text = selectedSymbol.id
                    )
                },
                headlineContent = {
                    Text(
                        text = selectedSymbol.name
                    )
                },
                trailingContent = {
                    IconButton(
                        onClick = onClickBackToSymbol
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = stringResource(R.string.select_arrow).format(
                                selectedSymbol.name
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
        if (orderBookData.asks.isNotEmpty() || orderBookData.bids.isNotEmpty()) {
            OrderBookComponent(
                data = OrderBookData(
                    title = orderBookData.symbolId,
                    entries = buildList {
                        addAll(
                            orderBookData.asks.map {
                                OrderBookEntry(
                                    type = OrderBookEntryType.ASK,
                                    price = it.price,
                                    size = it.size
                                )
                            }
                        )
                        addAll(
                            orderBookData.bids.map {
                                OrderBookEntry(
                                    type = OrderBookEntryType.BID,
                                    price = it.price,
                                    size = it.size
                                )
                            }
                        )
                    }
                )
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Spacing.large),
                text = stringResource(R.string.empty_list),
                style = MaterialTheme.typography.bodyLarge
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(AssetOrderBookPreviewParameters::class)
    orderBookData: UiOrderBookData,
) {
    AppTheme {
        Box(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
        ) {
            AssetOrderBookComponent(
                selectedExchange = UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                selectedSymbol = UiSymbolItem(
                    id = "SPW",
                    name = "PREVIEW_SPOT_PREVIEW",
                    hasCurrentOrderBook = true
                ),
                orderBookData = orderBookData,
                onClickBackToExchange = {},
                onClickBackToSymbol = {},
            )
        }
    }
}
