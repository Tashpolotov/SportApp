package com.example.domain.model.liga

data class ResponseTeamItem(
    val coaches: List<Coache>,
    val players: List<Player>,
    val team_badge: String,
    val team_key: String,
    val team_name: String
)