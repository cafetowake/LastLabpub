package lastlab.uvg.edu.gt.data.network.dto

import kotlinx.serialization.Serializable
import lastlab.uvg.edu.gt.data.data.local.Entity.CoinEntity
import lastlab.uvg.edu.gt.data.dc.Coin

@Serializable
data class CoinDto(
    val id: String,
    val name: String,
    val symbol: String,
    val supply: String,
    val priceUsd: Int,
    val changePercent24Hr: Int,
    val maxSupply: Int,
    val marketCapUsd: Int
)

fun CoinDto.mapToCoinModel(): Coin{
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        supply = supply,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd
    )
}

fun CoinDto.mapToCoinEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        name = name,
        symbol = symbol,
        supply = supply,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd
    )
}