package com.example.coinapp.feature.details_page.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem

class SymbolsListPreviewParameters : PreviewParameterProvider<List<UiSymbolItem>> {
    override val values: Sequence<List<UiSymbolItem>>
        get() = sequenceOf(
            emptyList(),
            listOf(
                UiSymbolItem(
                    id = "SPW",
                    name = "PREVIEW_SPOT_PREVIEW",
                    hasCurrentOrderBook = false
                ),
                UiSymbolItem(
                    id = "SPW",
                    name = "PREVIEW_SPOT_PREVIEW",
                    hasCurrentOrderBook = true
                ),
            ),
        )
}
