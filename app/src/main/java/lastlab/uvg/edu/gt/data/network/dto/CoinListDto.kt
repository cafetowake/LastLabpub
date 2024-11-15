package lastlab.uvg.edu.gt.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinListDto(
    val data: List<CoinDto>,
    val message: String,
    val status:Int
)