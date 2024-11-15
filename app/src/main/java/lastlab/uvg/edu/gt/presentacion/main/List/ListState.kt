package lastlab.uvg.edu.gt.presentacion.main.List

import lastlab.uvg.edu.gt.data.dc.Coin

data class ListState(
    val isLoading: Boolean = true,
    val data: List<Coin> = emptyList(),
    val isGenericError: Boolean = false,
    val noInternetConnection: Boolean = false
)