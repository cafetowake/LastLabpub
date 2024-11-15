package lastlab.uvg.edu.gt.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import lastlab.uvg.edu.gt.data.network.dto.CoinListDto
import lastlab.uvg.edu.gt.data.network.dto.CoinProfileDto
import lastlab.uvg.edu.gt.data.network.util.safeCall
import lastlab.uvg.edu.gt.domain.network.Api.CoinApi
import lastlab.uvg.edu.gt.domain.network.util.NetworkError
import lastlab.uvg.edu.gt.domain.network.util.Result

class KtorCoinApi(
    private val httpClient: HttpClient
): CoinApi {
    override suspend fun getAllCoin(): Result<CoinListDto, NetworkError> {
        return safeCall <CoinListDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets"
            )
        }
    }

    override suspend fun getCoinProfile(id: Int): Result<CoinProfileDto, NetworkError> {
        return safeCall<CoinProfileDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets/$id"
            )
        }
    }
}