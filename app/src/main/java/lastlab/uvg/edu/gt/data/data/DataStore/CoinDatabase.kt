package lastlab.uvg.edu.gt.data.data.DataStore

import androidx.room.Database
import androidx.room.RoomDatabase
import lastlab.uvg.edu.gt.data.data.local.Dao.CoinDao
import lastlab.uvg.edu.gt.data.data.local.Entity.CoinEntity

@Database(
    entities = [
        CoinEntity::class
    ],
    version = 1
)
abstract class CoinDatabase: RoomDatabase() {
    abstract fun CoinDao(): CoinDao
}