package com.example.cryptoapp.domain

data class CoinInfo    (
    var fromsymbol: String,
    var tosymbol: String?,
    var lastmarket: String?,
    var price: Double?,
    var lastupdate: String?,
    var highday: Double?,
    var lowday: Double?,
    var imageurl: String
)