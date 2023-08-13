package com.example.data.repository

import android.util.Log
import com.example.data.network.SportApiService
import com.example.domain.model.SportResponseItem
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseLigaItem
import com.example.domain.model.liga.ResponseTeamItem
import com.example.domain.repository.SportRepository
import com.example.domain.resource.Const.API_KEY

class SportRepositoryImpl(private val apiService: SportApiService) : SportRepository {

    override suspend fun getCountry(): List<SportResponseItem> {
        Log.e("SportRepositoryImpl", "getCountry() called")
        val response = apiService.getCountry(action = "get_countries", apiKey = API_KEY)
        Log.e("SportRepositoryImpl", "getCountry() API response received: $response")
        return response
    }

    override suspend fun getLeagues(countryId: String): List<ResponseLigaItem> {
        Log.e("SportRepositoryImpl", "getLeagues() called")
        val response = apiService.getLieagues(action = "get_leagues", apiKey = API_KEY, countryId = countryId)
        Log.e("SportRepositoryImpl", "getLeagues() API response received: $response")
        return response
    }

    override suspend fun getTeam(leagueId: String): List<ResponseTeamItem> {
        Log.e("SportRepositoryImpl", "getTeam() called")
        val response = apiService.getTeam(action = "get_teams", apiKey = API_KEY, leagueId = leagueId)
        Log.e("SportRepositoryImpl", "getTeam() API response received: $response")
        return response
    }
}