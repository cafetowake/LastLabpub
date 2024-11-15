package lastlab.uvg.edu.gt.di

import android.content.Context
import androidx.room.Room
import io.ktor.client.HttpClient
import lastlab.uvg.edu.gt.data.data.DataStore.CoinDatabase
import lastlab.uvg.edu.gt.data.network.HttpClientFactory

object KtorDependencies {
    private var httpClient: HttpClient? = null
    private var localDb: CoinDatabase? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    private fun buildLocalDb(context: Context): CoinDatabase {
        return Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            "coin.db"
        ).build()
    }

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }

    fun provideLocalDb(context: Context): CoinDatabase {
        return localDb ?: synchronized(this) {
            localDb ?: buildLocalDb(context).also { localDb = it }
        }
    }
}