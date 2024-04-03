package com.example.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.pojo.CoinPriceInfo


class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    lateinit var bindind: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(bindind.root)
        Log.d("TEST_OF_LOADING_DATA", "Y")
        val adapter = CoinInfoAdapter(this)
        bindind.rvCoinPriceList.adapter = adapter

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("ON_CLICK_TEST", coinPriceInfo.fromsymbol)
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromsymbol
                )
                startActivity(intent)
            }
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA", it.toString())
            adapter.coinInfoList = it
        })

    }
}