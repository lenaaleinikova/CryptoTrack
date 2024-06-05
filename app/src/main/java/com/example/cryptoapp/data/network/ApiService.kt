package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.model.CoinInfoListOfData
import com.example.cryptoapp.data.model.CoinPriceInfoRawData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "d07a37a2b79e1d76d3531600455e1daa6adbbe7db195c6bd3d45e5332d0c35cd"
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "d07a37a2b79e1d76d3531600455e1daa6adbbe7db195c6bd3d45e5332d0c35cd",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
        ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}