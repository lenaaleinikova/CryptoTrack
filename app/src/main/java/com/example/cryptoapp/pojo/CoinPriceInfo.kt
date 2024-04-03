package com.example.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.Api.ApiFactory.BASE_Image_URL
import com.example.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    var type: String? = null,

    @SerializedName("MARKET")
    @Expose
    var market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    var fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    var tosymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    var flags: String? = null,

    @SerializedName("LASTMARKET")
    @Expose
    var lastmarket: String? = null,

    @SerializedName("MEDIAN")
    @Expose
    var median: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    var toptiervolume24hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    var toptiervolume24hourto: Double? = null,

    @SerializedName("LASTTRADEID")
    @Expose
    var lasttradeid: String? = null,

    @SerializedName("PRICE")
    @Expose
    var price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    var lastupdate: Long? = null,

    @SerializedName("LASTVOLUME")
    @Expose
    var lastvolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    @Expose
    var lastvolumeto: Double? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    var volumehour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    var volumehourto: Double? = null,

    @SerializedName("OPENHOUR")
    @Expose
    var openhour: Double? = null,

    @SerializedName("HIGHHOUR")
    @Expose
    var highhour: Double? = null,

    @SerializedName("LOWHOUR")
    @Expose
    var lowhour: Double? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    var volumeday: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    var volumedayto: Double? = null,

    @SerializedName("OPENDAY")
    @Expose
    var openday: Double? = null,

    @SerializedName("HIGHDAY")
    @Expose
    var highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    var lowday: Double? = null,

    @SerializedName("VOLUME24HOUR")
    @Expose
    var volume24hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    var volume24hourto: Double? = null,

    @SerializedName("OPEN24HOUR")
    @Expose
    var open24hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    @Expose
    var high24hour: Double? = null,

    @SerializedName("LOW24HOUR")
    @Expose
    var low24hour: Double? = null,

    @SerializedName("CHANGE24HOUR")
    @Expose
    var change24hour: Double? = null,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    var changepct24hour: Double? = null,

    @SerializedName("CHANGEDAY")
    @Expose
    var changeday: Double? = null,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    var changepctday: Double? = null,

    @SerializedName("CHANGEHOUR")
    @Expose
    var changehour: Double? = null,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    var changepcthour: Double? = null,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    var conversiontype: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    var conversionsymbol: String? = null,

    @SerializedName("CONVERSIONLASTUPDATE")
    @Expose
    var conversionlastupdate: Int? = null,

    @SerializedName("SUPPLY")
    @Expose
    var supply: Int? = null,

    @SerializedName("MKTCAP")
    @Expose
    var mktcap: Double? = null,

    @SerializedName("MKTCAPPENALTY")
    @Expose
    var mktcappenalty: Int? = null,

    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    var circulatingsupply: Int? = null,

    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    var circulatingsupplymktcap: Double? = null,

    @SerializedName("TOTALVOLUME24H")
    @Expose
    var totalvolume24h: Double? = null,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    var totalvolume24hto: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    var totaltoptiervolume24h: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    var totaltoptiervolume24hto: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    var imageurl: String? = null
){
    fun getFormattedTime():String{
        return convertTimestampToTime(lastupdate)
    }

    fun getFullImageUrl():String{
        return BASE_Image_URL + imageurl
    }
}