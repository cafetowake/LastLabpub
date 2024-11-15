package lastlab.uvg.edu.gt.presentacion.main.Profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lastlab.uvg.edu.gt.data.network.KtorCoinApi
import lastlab.uvg.edu.gt.data.repository.CoinRepositoryImpl
import lastlab.uvg.edu.gt.di.KtorDependencies
import lastlab.uvg.edu.gt.domain.network.util.onError
import lastlab.uvg.edu.gt.domain.network.util.onSuccess
import lastlab.uvg.edu.gt.domain.repository.CoinRepository

class ProfileViewModel (
    savedStateHandle: SavedStateHandle,
    private val coinRepository: CoinRepository
): ViewModel(){
    private val profile = savedStateHandle.toRoute<ProfileDestination>()
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        getCoinProfile()
    }

    private fun getCoinProfile(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true,)
            }
            delay(1000L)
            coinRepository
                .getCoinProfile(id = profile.id)
                .onSuccess { coin ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coin = coin,
                            isGenericError = false
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isGenericError = true
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val context = checkNotNull(this[APPLICATION_KEY])
                val api = KtorCoinApi(KtorDependencies.provideHttpClient())
                val db = KtorDependencies.provideLocalDb(context = context)
                ProfileViewModel(
                    coinRepository = CoinRepositoryImpl(
                        api = api,
                        coinDao = db.CoinDao()
                    ),
                    savedStateHandle = this.createSavedStateHandle())
            }
        }
    }
}