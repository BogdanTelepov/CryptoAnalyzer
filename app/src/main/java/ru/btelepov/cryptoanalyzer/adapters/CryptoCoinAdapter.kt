package ru.btelepov.cryptoanalyzer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.btelepov.cryptoanalyzer.databinding.CoinItemLayoutBinding
import ru.btelepov.cryptoanalyzer.models.CryptoCoin
import ru.btelepov.cryptoanalyzer.utils.format
import ru.btelepov.cryptoanalyzer.utils.round


class CryptoCoinAdapter : RecyclerView.Adapter<CryptoCoinAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CoinItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<CryptoCoin>() {
        override fun areItemsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)


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
        val currentItem = differ.currentList[position]
        holder.binding.tvItemName.text = currentItem.name
        holder.binding.tvItemSymbol.text = currentItem.symbol
        holder.binding.tvItemPrice.text = "${currentItem.quote.uSD.price.format(2)} $"
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}