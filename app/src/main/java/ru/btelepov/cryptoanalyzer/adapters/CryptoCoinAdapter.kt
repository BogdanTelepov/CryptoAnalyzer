package ru.btelepov.cryptoanalyzer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.btelepov.cryptoanalyzer.databinding.CoinItemLayoutBinding
import ru.btelepov.cryptoanalyzer.models.CryptoCoin

import ru.btelepov.cryptoanalyzer.utils.CustomDiffUtil
import ru.btelepov.cryptoanalyzer.utils.format


class CryptoCoinAdapter : RecyclerView.Adapter<CryptoCoinAdapter.MyViewHolder>() {


    private var listCoins = emptyList<CryptoCoin>()

    class MyViewHolder(val binding: CoinItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)






    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            CoinItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listCoins[position]
        holder.binding.tvItemName.text = currentItem.name
        holder.binding.tvItemSymbol.text = currentItem.symbol
        holder.binding.tvItemPrice.text = "${currentItem.quote.uSD.price.format(2)} $"
    }

    override fun getItemCount(): Int {
        return listCoins.size
    }

    fun setData(newData: List<CryptoCoin>) {
        val diffUtil = CustomDiffUtil(listCoins, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        listCoins = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}