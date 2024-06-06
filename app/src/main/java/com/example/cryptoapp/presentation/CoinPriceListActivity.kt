package com.example.cryptoapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.domain.CoinInfo


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
            override fun onCoinClick(coinInfoDto: CoinInfo) {
                Log.d("ON_CLICK_TEST", coinInfoDto.fromsymbol)
                if (bindind.fragmentContainer == null){
                    launchDetailActivity(coinInfoDto.fromsymbol)
                }
                else{
                    launchDetailFragment(coinInfoDto.fromsymbol)
                }
            }
        }
        bindind.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA", it.toString())
            adapter.submitList(it)
        })

    }
    private fun launchDetailActivity(fromSymbol: String){
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }
    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}