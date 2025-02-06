package com.example.coinapp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.coinapp.R
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.core.theme.Dimens
import com.example.coinapp.core.ui.model.UiError

@Composable
fun ErrorPage(
    error: UiError
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        error.icon?.let {
            Icon(
                modifier = Modifier.size(Dimens.Size.big),
                imageVector = getErrorIcon(it),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
        }
        Text(
            text = error.message,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        )
        if (error.onRetry != null) {
            Spacer(modifier = Modifier.height(Dimens.Spacing.medium))
            FilledTonalButton(
                onClick = {
                    error.onRetry.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.retry_message),
                )
            }
        }

    }
}

private fun getErrorIcon(icon: UiError.ErrorIcon): ImageVector {
    return when (icon) {
        UiError.ErrorIcon.ALERT -> Icons.Default.Warning
        UiError.ErrorIcon.FAILURE -> Icons.Default.Close
    }
}

@Preview
@Composable
private fun Preview(
) {
    AppTheme {
        ErrorPage(
            UiError(
                message = "Please try again because we messed up here! \n Error: 500",
                onRetry = {},
                icon = UiError.ErrorIcon.FAILURE
            )
        )
    }
}
