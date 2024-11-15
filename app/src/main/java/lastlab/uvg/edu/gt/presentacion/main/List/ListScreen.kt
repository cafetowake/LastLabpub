package lastlab.uvg.edu.gt.presentacion.main.List

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import lastlab.uvg.edu.gt.R
import lastlab.uvg.edu.gt.data.dc.Coin
import lastlab.uvg.edu.gt.presentacion.common.ErrorView
import lastlab.uvg.edu.gt.presentacion.common.LoadingView


@Composable
fun ListRoute(
    onProfileClick: (Int) -> Unit,
    viewModel: ListViewModel = viewModel(factory = ListViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListScreen(
        state = state,
        onRetryClick = viewModel::getCoins,
        onProfileClick = onProfileClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun ListScreen(
    state: ListState,
    onRetryClick: () -> Unit,
    onProfileClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.isGenericError -> {
                ErrorView(
                    text = stringResource(R.string.retry),
                    onRetryClick = onRetryClick,
                    modifier = Modifier.align(Alignment.Center)

                )
            }

            state.noInternetConnection -> {
                ErrorView(
                    text = stringResource(R.string.retry),
                    onRetryClick = onRetryClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn {
                    items(state.data) { coin ->
                        ListItem(
                            coin = coin,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onProfileClick(coin.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItem(
    coin: Coin,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = coin.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = coin.symbol, style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = "$${coin.priceUsd}",
            style = MaterialTheme.typography.bodySmall,
            color = if (coin.changePercent24Hr.toFloat() > 0) Color.Green else Color.Red
        )
    }
}
