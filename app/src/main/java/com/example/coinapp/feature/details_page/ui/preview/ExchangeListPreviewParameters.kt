package com.example.coinapp.feature.details_page.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem

class ExchangeListPreviewParameters : PreviewParameterProvider<List<UiExchangeItem>> {
    override val values: Sequence<List<UiExchangeItem>>
        get() = sequenceOf(
            emptyList(),
            listOf(
                UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
            )
        )
}
