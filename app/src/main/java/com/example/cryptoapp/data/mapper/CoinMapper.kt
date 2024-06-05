package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.CoinInfoDbModel
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {
    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromsymbol = dto.fromsymbol,
        tosymbol = dto.tosymbol,
        price = dto.price,
        highday = dto.highday,
        lowday = dto.lowday,
        lastmarket = dto.lastmarket,
        lastupdate = dto.lastupdate,
        imageurl = dto.imageurl
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
        lastupdate = dbModel.lastupdate,
        imageurl = dbModel.imageurl
    )

}
