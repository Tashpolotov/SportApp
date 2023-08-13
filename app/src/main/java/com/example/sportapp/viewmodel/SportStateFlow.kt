package com.example.sportapp.viewmodel

import com.example.domain.model.SportResponseItem
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseLigaItem
import com.example.domain.model.liga.ResponseTeamItem


data class SportState(
        val country : List<SportResponseItem> = emptyList(),
        val leagues:List<ResponseLigaItem> = emptyList(),
                val team:List<ResponseTeamItem> = emptyList(),


)