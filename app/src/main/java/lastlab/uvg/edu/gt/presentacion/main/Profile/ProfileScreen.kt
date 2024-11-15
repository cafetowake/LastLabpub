package lastlab.uvg.edu.gt.presentacion.main.Profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import lastlab.uvg.edu.gt.R
import lastlab.uvg.edu.gt.presentacion.common.ErrorView
import lastlab.uvg.edu.gt.presentacion.common.LoadingView

@Composable
fun ProfileRoute(
    onBackClick: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProfileScreen(
        state = state,
        onBackClick = onBackClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun ProfileScreen(
    state: ProfileState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
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
                    onRetryClick = onBackClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                state.coin?.let { coin ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text(text = coin.name, style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Symbol: ${coin.symbol}")
                        Text(text = "Price (USD): $${coin.priceUsd}")
                        Text(text = "Change (24hr): ${coin.changePercent24Hr}%")
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Supply: ${coin.supply}")
                        Text(text = "Max Supply: ${coin.maxSupply}")
                        Text(text = "Market Cap (USD): $${coin.marketCapUsd}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    }
                }
            }
        }
    }
}