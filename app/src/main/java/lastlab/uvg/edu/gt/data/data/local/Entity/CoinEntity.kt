package lastlab.uvg.edu.gt.data.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import lastlab.uvg.edu.gt.data.dc.Coin

@Entity
data class CoinEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val supply: String,
    val priceUsd: Int,
    val changePercent24Hr: Int,
    val maxSupply: Int,
    val marketCapUsd: Int
)

fun CoinEntity.mapToCoinModel(): Coin{
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