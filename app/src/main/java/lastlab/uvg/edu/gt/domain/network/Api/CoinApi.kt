package lastlab.uvg.edu.gt.domain.network.Api

import lastlab.uvg.edu.gt.domain.network.util.NetworkError
import lastlab.uvg.edu.gt.data.network.dto.CoinListDto
import lastlab.uvg.edu.gt.data.network.dto.CoinProfileDto
import lastlab.uvg.edu.gt.domain.network.util.Result

interface CoinApi{
    suspend fun getAllCoin(): Result<CoinListDto, NetworkError>
    suspend fun getCoinProfile(id: Int): Result<CoinProfileDto, NetworkError>
}