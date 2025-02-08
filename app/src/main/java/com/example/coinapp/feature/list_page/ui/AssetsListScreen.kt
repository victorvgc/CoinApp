package com.example.coinapp.feature.list_page.ui

import android.content.res.Configuration
import android.icu.number.NumberFormatter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter
import com.example.coinapp.BuildConfig
import com.example.coinapp.R
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.feature.list_page.ui.model.UiAssetItem
import java.math.BigDecimal
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetsListScreen(
    list: List<UiAssetItem>,
    onItemClick: (UiAssetItem) -> Unit
) {
    val numberFormatter = NumberFormatter.with()
        .locale(Locale.getDefault())

    Column {
        TopAppBar(
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.app_name),
                    textAlign = TextAlign.Center
                )
            },
        )
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
                            text = stringResource(R.string.daily_volume).format(
                                numberFormatter.format(item.dailyVolume).toString()
                            )
                        )
                    },
                    leadingContent = {
                        Image(
                            modifier = Modifier.size(Dimens.Size.small),
                            painter = rememberAsyncImagePainter(
                                model = BuildConfig.ICONS_URL.format(item.iconId),
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
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    AppTheme {
        AssetsListScreen(
            list = listOf(
                UiAssetItem(
                    id = "BTC",
                    iconId = "",
                    name = "Bitcoin",
                    dailyVolume = BigDecimal("2086392323256.16")
                ),
                UiAssetItem(
                    id = "BTC",
                    iconId = "",
                    name = "Bitcoin",
                    dailyVolume = BigDecimal("2086392323256.16")
                ),
            )
        ) {}
    }
}
