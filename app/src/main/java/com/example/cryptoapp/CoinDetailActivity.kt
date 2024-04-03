package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol == null) {
            finish()
            return
        }
        viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
            bindind.tvPrice.text = it.price.toString()
            bindind.tvMinPrice.text = it.lowday.toString()
            bindind.tvMaxPrice.text = it.highday.toString()
            bindind.tvLastMarket.text = it.lastmarket
            bindind.tvLastUpdate.text = it.getFormattedTime()
            bindind.tvFromSymbol.text = it.fromsymbol
            bindind.tvToSymbol.text = it.tosymbol
            Picasso.get().load(it.getFullImageUrl()).into(bindind.ivLogoCoin)

        })
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}