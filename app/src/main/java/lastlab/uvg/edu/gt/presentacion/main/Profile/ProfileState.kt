package lastlab.uvg.edu.gt.presentacion.main.Profile

import lastlab.uvg.edu.gt.data.dc.Coin

data class ProfileState(
    val isLoading: Boolean = true,
    val coin: Coin? = null,
    val isGenericError: Boolean = false,
    val noInternetConnection: Boolean = false

)