package com.example.coinapp.feature.details_page.ui

import android.content.res.Configuration
import android.icu.number.NumberFormatter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil3.compose.rememberAsyncImagePainter
import com.example.coinapp.BuildConfig
import com.example.coinapp.R
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.feature.details_page.ui.model.AssetDetailsData
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem
import com.example.coinapp.feature.details_page.ui.preview.AssetDetailsPreviewParameters
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetDetailsScreen(
    data: AssetDetailsData,
    onNavigateBack: () -> Unit,
    onExchangeClick: (UiExchangeItem) -> Unit,
    onSymbolClick: (UiExchangeItem, UiSymbolItem) -> Unit,
    onBackToSymbolClick: (UiExchangeItem) -> Unit,
    onBackToExchangeClick: () -> Unit,
) {
    val numberFormatter = NumberFormatter.with()
        .locale(Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
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
                    text = data.assetId,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onNavigateBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_arrow)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
        Header(
            assetName = data.assetName,
            assetPrice = numberFormatter.format(data.assetPrice).toString(),
            assetDailyVolume = numberFormatter.format(data.assetDailyVolume).toString(),
            assetIconUrl = BuildConfig.ICONS_URL.format(data.assetIconId),
        )
        Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
        when (data) {
            is AssetDetailsData.InitialData -> {
                ExchangeListComponent(
                    list = data.exchangeList,
                    onItemClick = onExchangeClick
                )
            }

            is AssetDetailsData.SelectedExchangeData -> {
                SymbolsListComponent(
                    selectedExchange = data.selectedExchange,
                    list = data.symbolsList,
                    onItemClick = onSymbolClick,
                    onBackClick = onBackToExchangeClick
                )
            }

            is AssetDetailsData.SelectedExchangeSymbolData -> {
                AssetOrderBookComponent(
                    selectedExchange = data.selectedExchange,
                    selectedSymbol = data.selectedSymbol,
                    orderBookData = data.orderBookData,
                    onClickBackToExchange = onBackToExchangeClick,
                    onClickBackToSymbol = onBackToSymbolClick
                )
            }
        }
    }
}

@Composable
private fun Header(
    assetIconUrl: String,
    assetName: String,
    assetPrice: String,
    assetDailyVolume: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium)
    ) {
        Image(
            modifier = Modifier.size(Dimens.Size.big),
            painter = rememberAsyncImagePainter(
                model = assetIconUrl,
                error = painterResource(R.drawable.broken_image),
                fallback = painterResource(R.drawable.broken_image),
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column {
            Text(
                text = assetName,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
            Text(
                text = stringResource(R.string.asset_daily_volume).format(assetDailyVolume),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Spacer(modifier = Modifier.height(Dimens.Spacing.small))
            Text(
                text = stringResource(R.string.asset_price).format(assetPrice),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(
    @PreviewParameter(AssetDetailsPreviewParameters::class)
    uiData: AssetDetailsData
) {
    AppTheme {
        AssetDetailsScreen(
            data = uiData,
            onSymbolClick = { _, _ -> },
            onExchangeClick = {},
            onNavigateBack = {},
            onBackToExchangeClick = {},
            onBackToSymbolClick = {},
        )
    }
}
