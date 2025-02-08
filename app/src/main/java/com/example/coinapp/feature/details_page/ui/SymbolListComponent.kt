package com.example.coinapp.feature.details_page.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
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
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem
import com.example.coinapp.feature.details_page.ui.preview.SymbolsListPreviewParameters

@Composable
fun SymbolsListComponent(
    selectedExchange: UiExchangeItem,
    list: List<UiSymbolItem>,
    onBackClick: () -> Unit,
    onItemClick: (UiExchangeItem, UiSymbolItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        ListItem(
            overlineContent = {
                Text(
                    text = selectedExchange.id,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            headlineContent = {
                Text(
                    text = selectedExchange.name,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            trailingContent = {
                IconButton(
                    onClick = onBackClick
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
            Text(
                modifier = Modifier.padding(horizontal = Dimens.Spacing.medium),
                text = stringResource(R.string.select_symbol),
                style = MaterialTheme.typography.titleSmall
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
            if (list.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(items = list) { index, item ->
                        ListItem(
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                headlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                overlineColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            ),
                            modifier = Modifier.clickable {
                                onItemClick(selectedExchange, item)
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
                            trailingContent = {
                                if (item.hasCurrentOrderBook) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = stringResource(R.string.select_arrow).format(
                                            item.name
                                        ),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        )

                        if (index != list.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.Spacing.large),
                    text = stringResource(R.string.empty_list),
                    style = MaterialTheme.typography.bodyLarge
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(SymbolsListPreviewParameters::class)
    list: List<UiSymbolItem>
) {
    AppTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
            SymbolsListComponent(
                selectedExchange = UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                list = list,
                onItemClick = { _, _ -> },
                onBackClick = {}
            )
        }
    }
}
