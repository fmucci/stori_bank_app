package com.francomucci.storitest.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.francomucci.storitest.R
import com.francomucci.storitest.ui.navigation.TRANSACTION_DETAIL_SCREEN
import com.francomucci.storitest.ui.screen.transaction.TransactionRow
import com.francomucci.storitest.ui.uikit.components.AppToolbar
import com.francomucci.storitest.ui.uikit.components.StoriText
import com.francomucci.storitest.ui.uikit.theme.FONT_NORMAL
import com.francomucci.storitest.ui.uikit.toBalanceModifier
import com.francomucci.storitest.ui.uikit.toTextModifier
import com.francomucci.storitest.ui.uikit.toTinySpacer
import com.francomucci.storitest.ui.uikit.toToolbarModifier

@Composable
fun HomeScreen(
    navigate: (String) -> Unit,
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    viewModel.getUser()
    Column(modifier) {
        AppToolbar(
            title = stringResource(id = R.string.home_app_title) + " ${viewModel.user.value.name}",
            actions = {
                viewModel.logOut(navigate)
            },
            modifier = modifier.toToolbarModifier(),
        )
        Spacer(modifier = modifier.toTinySpacer())
        Card(
            modifier = modifier.toBalanceModifier()
        ) {
            StoriText(
                "Balance: " + viewModel.user.value.balance,
                modifier = modifier.toTextModifier(),
            )
        }
        Spacer(modifier = modifier.toTinySpacer())
        Text(
            stringResource(id = R.string.transactions),
            fontSize = FONT_NORMAL,
            modifier = modifier.toTextModifier(),
        )
        Spacer(modifier = modifier.toTinySpacer())
        LazyColumn {
            items(viewModel.user.value.transactions) { transaction ->
                TransactionRow(
                    modifier = modifier.clickable {
                        navigate("$TRANSACTION_DETAIL_SCREEN/" + transaction.id)
                    },
                    transaction = transaction,
                )
            }
        }
    }
}