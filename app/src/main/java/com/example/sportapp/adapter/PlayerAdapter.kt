package com.example.sportapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseLigaItem
import com.example.sportapp.databinding.ItemPlayerDetailsBinding

class PlayerAdapter:ListAdapter<Player, PlayerAdapter.PlayerViewHolder>(PlayerDiffutil()) {
   inner class PlayerViewHolder(val binding:ItemPlayerDetailsBinding):RecyclerView.ViewHolder(binding.root) {
       fun bind(model: Player) {
           binding.tvPlayersName.text = model.player_name
           binding.tvPlayerNumber.text = model.player_number
           binding.tvGoal.text = model.player_goals
           binding.tvPosition.text =  model.player_type
           binding.tvGamePlay.text = model.player_match_played
           binding.tvCountry.text = model.player_country
           Glide.with(binding.root)
               .load(model.player_image)
               .into(binding.imgPlayer)
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(ItemPlayerDetailsBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlayerDiffutil:DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem ==newItem
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem ==newItem
    }

}
