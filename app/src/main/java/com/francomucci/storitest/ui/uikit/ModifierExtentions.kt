package com.francomucci.storitest.ui.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.francomucci.storitest.ui.uikit.theme.HUGE_DIMEN
import com.francomucci.storitest.ui.uikit.theme.SMALL_DIMEN
import com.francomucci.storitest.ui.uikit.theme.TINY_DIMEN
import com.francomucci.storitest.ui.uikit.theme.ZERO_DIMEN

fun Modifier.toToolbarModifier() =
    this.padding(ZERO_DIMEN)

fun Modifier.toTextModifier() =
    this
        .fillMaxWidth()
        .padding(
            horizontal = SMALL_DIMEN,
            vertical = TINY_DIMEN,
        )

fun Modifier.toRowTextModifier() =
    this
        .padding(
            horizontal = SMALL_DIMEN,
            vertical = TINY_DIMEN,
        )


fun Modifier.toBalanceModifier() =
    this
        .padding(
            horizontal = SMALL_DIMEN,
        )

fun Modifier.toTextFieldModifier() =
    this
        .fillMaxWidth()
        .padding(
            horizontal = SMALL_DIMEN,
            vertical = TINY_DIMEN,
        )

fun Modifier.toButtonModifier() =
    this
        .fillMaxWidth()
        .padding(
            horizontal = SMALL_DIMEN,
            vertical = TINY_DIMEN,
        )

fun Modifier.toSmallSpacer() =
    this
        .fillMaxWidth()
        .padding(SMALL_DIMEN)

fun Modifier.toTinySpacer() =
    this
        .fillMaxWidth()
        .padding(TINY_DIMEN)

fun Modifier.toHugeSpacer() =
    this
        .fillMaxWidth()
        .padding(HUGE_DIMEN)

fun Modifier.toTransactionRow() =
    this
        .fillMaxWidth()
        .padding(
            horizontal = SMALL_DIMEN,
            vertical = TINY_DIMEN,
        )