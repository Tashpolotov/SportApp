package com.example.sportapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.liga.ResponseLigaItem
import com.example.sportapp.databinding.ItemLigaBinding

class LigaAdapter(val onClick:(String) -> Unit):ListAdapter<ResponseLigaItem, LigaAdapter.LigaViewHolder>(LigaDiffutil()) {
    inner class LigaViewHolder(val binding: ItemLigaBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ResponseLigaItem) {
            Glide.with(binding.root)
                .load(model.country_logo)
                .into(binding.imgCountry)
            binding.tvCountry.text = model.country_name
            Glide.with(binding.root)
                .load(model.league_logo)
                .into(binding.imgLeague)
            binding.tvLeagueName.text = model.league_name
            binding.tvLeagueSeason.text = model.league_season

            itemView.setOnClickListener {
                onClick(model.league_id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigaViewHolder {
        return LigaViewHolder(ItemLigaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LigaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LigaDiffutil:DiffUtil.ItemCallback<ResponseLigaItem>() {
    override fun areItemsTheSame(oldItem: ResponseLigaItem, newItem: ResponseLigaItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResponseLigaItem, newItem: ResponseLigaItem): Boolean {
        return oldItem == newItem
    }

}
