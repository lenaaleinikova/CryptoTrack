package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.CoinInfoDbModel
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CoinMapper @Inject constructor(){
    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromsymbol = dto.fromsymbol,
        tosymbol = dto.tosymbol,
        price = dto.price,
        highday = dto.highday,
        lowday = dto.lowday,
        lastmarket = dto.lastmarket,
        lastupdate = dto.lastupdate,
        imageurl = BASE_IMAGE_URL + dto.imageurl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",").toString()
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromsymbol = dbModel.fromsymbol,
        tosymbol = dbModel.tosymbol,
        price = dbModel.price,
        highday = dbModel.highday,
        lowday = dbModel.lowday,
        lastmarket = dbModel.lastmarket,
        lastupdate = convertTimestampToTime(dbModel.lastupdate),
        imageurl = dbModel.imageurl
    )
    private fun convertTimestampToTime(timestamp: Long?):String{
        if(timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
    companion object{
        const val BASE_IMAGE_URL ="https://cryptocompare.com"
    }
}
