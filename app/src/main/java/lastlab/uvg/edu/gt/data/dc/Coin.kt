package lastlab.uvg.edu.gt.data.dc

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val supply: String,
    val priceUsd: Int,
    val changePercent24Hr: Int,
    val maxSupply: Int,
    val marketCapUsd: Int
)