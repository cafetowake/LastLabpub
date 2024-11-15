package lastlab.uvg.edu.gt.data.repository

import lastlab.uvg.edu.gt.data.data.local.Dao.CoinDao
import lastlab.uvg.edu.gt.data.dc.Coin
import lastlab.uvg.edu.gt.data.dc.DataError
import lastlab.uvg.edu.gt.domain.network.Api.CoinApi
import lastlab.uvg.edu.gt.domain.network.util.Result
import lastlab.uvg.edu.gt.domain.repository.CoinRepository
import lastlab.uvg.edu.gt.domain.network.util.NetworkError
import lastlab.uvg.edu.gt.data.data.local.Entity.mapToCoinModel
import lastlab.uvg.edu.gt.data.network.dto.mapToCoinEntity
import lastlab.uvg.edu.gt.data.network.dto.mapToCoinModel

class CoinRepositoryImpl(
    private val api: CoinApi,
    private val coinDao: CoinDao
): CoinRepository {
    override suspend fun getAllCoin(): Result<List<Coin>, DataError> {
        when (val result = api.getAllCoin()) {
            is Result.Error -> {
                println(result.error)

                val localMonsters = coinDao.getAllCoin()
                if (localMonsters.isEmpty()) {
                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    }

                    return Result.Error(
                        DataError.GENERIC_ERROR
                    )
                } else {
                    return Result.Success(
                        localMonsters.map { it.mapToCoinModel() }
                    )
                }
            }

            is Result.Success -> {
                val remoteMonsters = result.data.data
                coinDao.insertCoin(
                    remoteMonsters.map { it.mapToCoinEntity() }
                )
                return Result.Success(
                    remoteMonsters.map { it.mapToCoinModel() }
                )
            }
        }
    }

    override suspend fun getCoinProfile(id: String): Result<Coin, DataError> {
        val localCoin = coinDao.getCoinById(id)
        return Result.Success(
            localCoin.mapToCoinModel()
        )
    }
}