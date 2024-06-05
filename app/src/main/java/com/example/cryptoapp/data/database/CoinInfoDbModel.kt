package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.data.network.ApiFactory.BASE_Image_URL
import com.example.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    var fromsymbol: String,
    var tosymbol: String?,
    var lastmarket: String?,
    var price: Double?,
    var lastupdate: Long?,
    var highday: Double?,
    var lowday: Double?,
    var imageurl: String?
)
