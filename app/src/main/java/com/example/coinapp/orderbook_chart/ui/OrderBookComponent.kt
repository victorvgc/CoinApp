package com.example.coinapp.orderbook_chart.ui

import android.content.res.Configuration
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.coinapp.R
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.orderbook_chart.model.OrderBookData
import com.example.coinapp.orderbook_chart.model.OrderBookEntryType
import java.util.Locale

private const val MIN_DECIMAL_DIGITS = 2
private const val MAX_DECIMAL_DIGITS = 8

@Composable
fun OrderBookComponent(
    data: OrderBookData
) {
    val context = LocalContext.current

    val bidEntries = data.entries.filter { it.type == OrderBookEntryType.BID }.sortedBy { it.price }
        .reversed()
    val askEntries = data.entries.filter { it.type == OrderBookEntryType.ASK }.sortedBy { it.price }

    Column {
        ElevatedCard(
            shape = RectangleShape,
            colors = CardDefaults.cardColors()
                .copy(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = Dimens.Elevation.far)
        ) {
            if (data.entries.isNotEmpty()) {
                OrderBookChart(data)
            } else {
                Text(
                    text = "Empty Data",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.Spacing.medium)
            ) {
                val headerStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = context.getString(R.string.size),
                    style = headerStyle
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = context.getString(R.string.bids),
                    style = headerStyle
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = context.getString(R.string.asks),
                    style = headerStyle
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = context.getString(R.string.size),
                    style = headerStyle
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            val numberFormatter = NumberFormatter.with()
                .locale(Locale.getDefault())
                .precision(Precision.minMaxFraction(MIN_DECIMAL_DIGITS, MAX_DECIMAL_DIGITS))

            val bodyStyle = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = Dimens.Spacing.medium),
                verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium)
            ) {
                bidEntries.forEach {
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = numberFormatter.format(it.size).toString(),
                            style = bodyStyle
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = context.getString(R.string.currency_prefix).format(numberFormatter.format(it.price).toString()),
                            style = bodyStyle
                        )
                    }
                }
            }

            VerticalDivider()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = Dimens.Spacing.medium),
                verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium)
            ) {
                askEntries.forEach {
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = context.getString(R.string.currency_prefix).format(numberFormatter.format(it.price).toString()),
                            style = bodyStyle
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = numberFormatter.format(it.size).toString(),
                            style = bodyStyle
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(
    @PreviewParameter(OrderBookPreviewParameters::class)
    orderBookData: OrderBookData
) {
    AppTheme {
        Surface {
            OrderBookComponent(orderBookData)
        }
    }
}
