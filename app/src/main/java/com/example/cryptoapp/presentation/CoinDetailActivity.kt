package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    lateinit var bindind: ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        bindind = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(bindind.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol == null) {
            finish()
            return
        }
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            bindind.tvPrice.text = it.price.toString()
            bindind.tvMinPrice.text = it.lowday.toString()
            bindind.tvMaxPrice.text = it.highday.toString()
            bindind.tvLastMarket.text = it.lastmarket
            bindind.tvLastUpdate.text = it.lastupdate
            bindind.tvFromSymbol.text = it.fromsymbol
            bindind.tvToSymbol.text = it.tosymbol
            Picasso.get().load(it.imageurl).into(bindind.ivLogoCoin)
        }

    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}