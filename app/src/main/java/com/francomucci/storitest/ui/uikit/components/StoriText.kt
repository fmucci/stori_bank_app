package com.francomucci.storitest.ui.uikit.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.francomucci.storitest.ui.uikit.toTextModifier

@Composable
fun StoriText(
    text: String,
    modifier: Modifier,
    size: TextUnit = 15.sp,
    bold: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier.toTextModifier(),
        fontSize = size,
        textAlign = textAlign,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun StoriTextPreview() {
    StoriText(
        text = "Some text",
        modifier = Modifier,
        size = 30.sp,
    )
}