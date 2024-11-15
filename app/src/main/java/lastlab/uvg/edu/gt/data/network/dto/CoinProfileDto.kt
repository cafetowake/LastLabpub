package lastlab.uvg.edu.gt.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinProfileDto(
    val data: CoinDto,
    val message: String,
    val status:Int
)