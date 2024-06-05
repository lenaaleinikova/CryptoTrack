package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    //    lateinit var bindind: ItemCoinInfoBinding
    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinInfoViewHolder(binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivLogoCoin = binding.ivLogoCoin
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvTime = binding.tvTime

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdate = context.resources.getString(R.string.last_update)
                tvSymbols.text = String.format(symbolsTemplate, fromsymbol, tosymbol)
                tvPrice.text = price.toString()
                tvTime.text = String.format(lastUpdate, lastupdate)
                Picasso.get().load(imageurl).into(ivLogoCoin)
                itemView.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }

    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfoDto: CoinInfo)
    }

}