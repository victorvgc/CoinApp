package com.example.coinapp.feature.details_page.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
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
import com.example.coinapp.feature.details_page.ui.preview.ExchangeListPreviewParameters

@Composable
fun ExchangeListComponent(
    list: List<UiExchangeItem>,
    onItemClick: (UiExchangeItem) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = Dimens.Spacing.medium),
            text = stringResource(R.string.select_exchange),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(Dimens.Spacing.small))
        if (list.isNotEmpty()) {
            LazyColumn {
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
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = stringResource(R.string.select_arrow).format(
                                    item.name
                                ),
                                tint = MaterialTheme.colorScheme.primary
                            )
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
                    .fillMaxWidth()
                    .padding(Dimens.Spacing.large),
                text = stringResource(R.string.empty_list),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ExchangeListPreviewParameters::class)
    list: List<UiExchangeItem>
) {
    AppTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
            ExchangeListComponent(
                list = list
            ) { }
        }
    }
}
