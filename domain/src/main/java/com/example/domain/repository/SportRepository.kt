package com.example.domain.repository

import com.example.domain.model.SportResponseItem
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseLigaItem
import com.example.domain.model.liga.ResponseTeamItem

interface SportRepository {

    suspend fun getCountry():List<SportResponseItem>

    suspend fun getLeagues(countryName:String):List<ResponseLigaItem>

    suspend fun getTeam(leagueId:String):List<ResponseTeamItem>


}