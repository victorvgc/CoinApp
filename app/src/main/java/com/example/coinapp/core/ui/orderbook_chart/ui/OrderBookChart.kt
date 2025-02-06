package com.example.coinapp.core.ui.orderbook_chart.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.copy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookData
import com.example.coinapp.core.ui.orderbook_chart.model.OrderBookEntryType

private const val CHART_HEIGHT = 150
private const val ALPHA_HALF = .5f

@Composable
fun OrderBookChart(
    data: OrderBookData
) {
    val bidColor = MaterialTheme.colorScheme.primary
    val askColor = MaterialTheme.colorScheme.tertiary

    var minAskEntry = data.entries.first { it.type == OrderBookEntryType.ASK }
    var maxAskEntry = minAskEntry

    var minBidEntry = data.entries.first { it.type == OrderBookEntryType.BID }
    var maxBidEntry = minBidEntry

    var absMinEntry = data.entries.first()
    var absMaxEntry = absMinEntry

    data.entries.forEach {
        if (absMinEntry.size > it.size) {
            absMinEntry = it
        }

        if (absMaxEntry.size < it.size) {
            absMaxEntry = it
        }

        when (it.type) {
            OrderBookEntryType.BID -> {
                if (minBidEntry.size > it.size) {
                    minBidEntry = it
                }

                if (maxBidEntry.size < it.size) {
                    maxBidEntry = it
                }
            }

            OrderBookEntryType.ASK -> {
                if (minAskEntry.size > it.size) {
                    minAskEntry = it
                }

                if (maxAskEntry.size < it.size) {
                    maxAskEntry = it
                }
            }
        }
    }

    val askEntries =
        data.entries.filter { it.type == OrderBookEntryType.ASK }.sortedBy { it.price }

    val bidEntries =
        data.entries.filter { it.type == OrderBookEntryType.BID }.sortedBy { it.price }

    val sortedEntries = buildList {
        addAll(bidEntries)
        addAll(askEntries.asReversed())
    }

    val bidBrush = Brush.verticalGradient(
        colors = listOf(
            bidColor.copy(alpha = ALPHA_HALF),
            Color.Transparent
        )
    )
    val askBrush = Brush.verticalGradient(
        colors = listOf(
            askColor.copy(alpha = ALPHA_HALF),
            Color.Transparent
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(CHART_HEIGHT.dp)
            .padding(top = Dimens.Spacing.medium)
    ) {
        val columnSize = size.width / data.entries.size
        val rowSize = size.height / (absMaxEntry.size - absMinEntry.size).toFloat()

        val pointsList = sortedEntries.mapIndexed { index, entry ->
            OrderBookPoint(
                type = entry.type,
                y = rowSize * ((absMaxEntry.size - entry.size).toFloat()),
                x = columnSize * index,
                value = entry.size.toPlainString()
            )
        }

        val bidPath = Path()
        val askPath = Path()

        bidPath.moveTo(pointsList.first { it.type == OrderBookEntryType.BID }.x, pointsList.first { it.type == OrderBookEntryType.BID }.y)
        askPath.moveTo(pointsList.first { it.type == OrderBookEntryType.ASK }.x, pointsList.first { it.type == OrderBookEntryType.ASK }.y)

        pointsList.forEach {
            when(it.type) {
                OrderBookEntryType.BID -> {
                    bidPath.lineTo(it.x, it.y)
                    bidPath.lineTo(it.x + columnSize, it.y)
                }
                OrderBookEntryType.ASK -> {
                    askPath.lineTo(it.x, it.y)
                    askPath.lineTo(it.x + columnSize, it.y)
                }
            }
        }

        val bidStrokePath = bidPath.copy()
        bidStrokePath.lineTo(pointsList.first { it.type == OrderBookEntryType.ASK }.x, pointsList.first { it.type == OrderBookEntryType.ASK }.y)
        val askStrokePath = askPath.copy()

        bidPath.lineTo(pointsList.first { it.type == OrderBookEntryType.ASK }.x, size.height)
        bidPath.lineTo(pointsList.first { it.type == OrderBookEntryType.BID }.x, size.height)
        bidPath.close()

        askPath.lineTo(pointsList.last { it.type == OrderBookEntryType.ASK }.x, size.height)
        askPath.lineTo(pointsList.first { it.type == OrderBookEntryType.ASK }.x, size.height)
        askPath.close()

        drawPath(
            path = bidStrokePath,
            color = bidColor,
            style = Stroke(width = Dimens.Thickness.medium.toPx())
        )
        drawPath(
            path = bidPath,
            brush = bidBrush,
        )

        drawPath(
            path = askStrokePath,
            color = askColor,
            style = Stroke(width = Dimens.Thickness.medium.toPx())
        )
        drawPath(
            path = askPath,
            brush = askBrush,
        )
    }
}

private data class OrderBookPoint(
    val value: String,
    val x: Float,
    val y: Float,
    val type: OrderBookEntryType
)
