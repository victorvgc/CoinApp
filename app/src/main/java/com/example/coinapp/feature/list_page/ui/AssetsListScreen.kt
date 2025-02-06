package com.example.coinapp.feature.list_page.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter
import com.example.coinapp.R
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.feature.list_page.ui.model.UiAssetItem

@Composable
fun AssetsListScreen(
    list: List<UiAssetItem>,
    onItemClick: (UiAssetItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
    ) {
        itemsIndexed(items = list) { index, item ->
            ListItem(
                modifier = Modifier.clickable {
                    onItemClick(item)
                },
                overlineContent = {
                    Text(
                        text = item.id
                    )
                },
                headlineContent = {
                    Text(
                        text = item.name
                    )
                },
                supportingContent = {
                    Text(
                        text = item.dailyVolume
                    )
                },
                leadingContent = {
                    Image(
                        modifier = Modifier.size(Dimens.Size.small),
                        painter = rememberAsyncImagePainter(
                            model = item.iconUrl,
                            error = painterResource(R.drawable.broken_image),
                            fallback = painterResource(R.drawable.broken_image),
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
            )
            if (index != list.size - 1) {
                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        AssetsListScreen(
            list = listOf(
                UiAssetItem(
                    id = "BTC",
                    iconUrl = "",
                    name = "Bitcoin",
                    dailyVolume = "$2.086.392.323.256,16 USD"
                ),
                UiAssetItem(
                    id = "BTC",
                    iconUrl = "",
                    name = "Bitcoin",
                    dailyVolume = "$2.086.392.323.256,16 USD"
                ),
            )
        ) {}
    }
}
