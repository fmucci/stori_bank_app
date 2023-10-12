package com.francomucci.storitest.ui.screen.transaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.francomucci.storitest.domain.model.Transaction
import com.francomucci.storitest.ui.uikit.theme.BORDER_DIMEN
import com.francomucci.storitest.ui.uikit.theme.Grey300
import com.francomucci.storitest.ui.uikit.theme.Grey400
import com.francomucci.storitest.ui.uikit.theme.LARGE_DIMEN
import com.francomucci.storitest.ui.uikit.toRowTextModifier
import com.francomucci.storitest.ui.uikit.toTransactionRow

@Composable
fun TransactionRow(transaction: Transaction, modifier: Modifier) {
    Card(
        border = BorderStroke(BORDER_DIMEN, Grey400),
        modifier = Modifier.toTransactionRow(),
        colors = CardDefaults.cardColors(
            containerColor = Grey300,
        ),
    ) {
        Row(
            modifier = modifier
                .height(LARGE_DIMEN)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = transaction.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .toRowTextModifier(),
            )
            Text(
                text = transaction.value.toString(),
                modifier = modifier
                    .toRowTextModifier()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun TransactionRowPreview() {
    TransactionRow(
        Transaction(
            title = "H&M",
            value = 12.17f,
            date = "29/11/2022",
        ),
        modifier = Modifier,
    )
}