package com.example.sportapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseTeamItem
import com.example.sportapp.databinding.ItemFootballClubBinding
import com.example.sportapp.databinding.ItemPlayerDetailsBinding

class TeamAdapter(val onClick:(ResponseTeamItem) -> Unit):ListAdapter<ResponseTeamItem, TeamAdapter.TeamViewHolder>(TeamDiffutil()) {
    inner class TeamViewHolder(val binding: ItemFootballClubBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ResponseTeamItem) {
            binding.tvClubName.text = model.team_name
            Glide.with(binding.root)
                .load(model.team_badge)
                .into(binding.imgClubAvatar)
                itemView.setOnClickListener {
                    onClick(model)
                }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(ItemFootballClubBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TeamDiffutil:DiffUtil.ItemCallback<ResponseTeamItem>() {
    override fun areItemsTheSame(oldItem: ResponseTeamItem, newItem: ResponseTeamItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResponseTeamItem, newItem: ResponseTeamItem): Boolean {
        return oldItem == newItem
    }

}
