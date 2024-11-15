package lastlab.uvg.edu.gt.domain.repository

import lastlab.uvg.edu.gt.data.dc.Coin
import lastlab.uvg.edu.gt.data.dc.DataError
import lastlab.uvg.edu.gt.domain.network.util.Result

interface CoinRepository{
    suspend fun getAllCoin(): Result<List<Coin>, DataError>
    suspend fun getCoinProfile(id: String): Result<Coin, DataError>
}