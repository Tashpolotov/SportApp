package com.example.sportapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.SportResponseItem
import com.example.sportapp.databinding.ItemCountryBinding

class CountryAdapter(val onClick:(String) -> Unit):ListAdapter<SportResponseItem, CountryAdapter.CountryViewHolder>(CountryDiffUtill()) {
    inner class CountryViewHolder(val binding:ItemCountryBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SportResponseItem)= with(binding){
            tvCountry.text = model.country_name
            Glide.with(binding.root)
                .load(model.country_logo)
                .into(binding.imgCountry)
            itemView.setOnClickListener {
                onClick(model.country_id.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CountryDiffUtill:DiffUtil.ItemCallback<SportResponseItem>() {
    override fun areItemsTheSame(oldItem: SportResponseItem, newItem: SportResponseItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: SportResponseItem,
        newItem: SportResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}
