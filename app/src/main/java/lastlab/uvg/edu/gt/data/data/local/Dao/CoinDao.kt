package lastlab.uvg.edu.gt.data.data.local.Dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import lastlab.uvg.edu.gt.data.data.local.Entity.CoinEntity

@Dao
interface CoinDao{
    @Query("SELECT * FROM CoinEntity")
    suspend fun getAllCoin(): List<CoinEntity>

    @Query("SELECT * FROM CoinEntity WHERE id = :id")
    suspend fun getCoinById(id: String): CoinEntity

    @Upsert
    suspend fun insertCoin(coins: List<CoinEntity>)

}