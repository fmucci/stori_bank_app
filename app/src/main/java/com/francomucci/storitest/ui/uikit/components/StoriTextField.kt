package com.francomucci.storitest.ui.uikit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.francomucci.storitest.R

@Composable
fun StoriTextField(
    value: String,
    modifier: Modifier,
    label: String?,
    maxLines: Int = 1,
    isError: Boolean = false,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        maxLines = maxLines,
        supportingText = {
            if (isError && !errorMessage.isNullOrEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = modifier,
        label = label?.let { { Text(it) } },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun StoriTextFieldPreview() {
    StoriTextField(
        label = stringResource(id = R.string.mail),
        onValueChange = {},
        value = "Some text",
        modifier = Modifier,
    )
}