package lastlab.uvg.edu.gt.presentacion.main.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lastlab.uvg.edu.gt.data.dc.DataError
import lastlab.uvg.edu.gt.data.network.KtorCoinApi
import lastlab.uvg.edu.gt.data.repository.CoinRepositoryImpl
import lastlab.uvg.edu.gt.di.KtorDependencies
import lastlab.uvg.edu.gt.domain.network.util.onError
import lastlab.uvg.edu.gt.domain.network.util.onSuccess
import lastlab.uvg.edu.gt.domain.repository.CoinRepository

class ListViewModel(
    private val coinRepository: CoinRepository
): ViewModel(){
    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

    init {
        getCoins()
    }

    fun getCoins(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true,
                isGenericError = false,
                noInternetConnection = false
            )}

            coinRepository
                .getAllCoin()
                .onSuccess { coin ->
                    _state.update { it.copy(
                        isLoading = false,
                        data = coin
                    ) }
                }
                .onError { error ->
                    if (error == DataError.NO_INTERNET) {
                        _state.update { it.copy(
                            isLoading = false,
                            noInternetConnection = true
                        ) }
                    } else {
                        _state.update { it.copy(
                            isLoading = false,
                            isGenericError = true,
                        ) }
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val api = KtorCoinApi(KtorDependencies.provideHttpClient())
                val db = KtorDependencies.provideLocalDb(
                    context = context
                )
                ListViewModel(
                    coinRepository = CoinRepositoryImpl(
                        api = api,
                        coinDao = db.CoinDao()
                    )
                )
            }
        }
    }

}