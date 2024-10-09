package com.example.cryptoapp.data.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay
import androidx.work.NetworkType
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfo: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {



    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .build()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfo.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(1000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED) // Ограничение по Wi-Fi
                .build()

            return OneTimeWorkRequestBuilder<RefreshDataWorker>()
//                .setConstraints(constraints)
                .build()
        }
    }
    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinMapper
    ) : ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                apiService,
                mapper
            )
        }
    }
}