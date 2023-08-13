package com.example.data.network

import com.example.domain.model.SportResponseItem
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseLigaItem
import com.example.domain.model.liga.ResponseTeamItem
import retrofit2.http.GET
import retrofit2.http.Query

interface SportApiService {

    //https://apiv3.apifootball.com/?action=get_countries&APIkey=

    //https://apiv3.apifootball.com/?action=get_teams&league_id=302&APIkey

    @GET("apiV3")
    suspend fun getCountry(
        @Query("action") action:String,
        @Query("APIkey") apiKey:String
    ):List<SportResponseItem>

    @GET("apiV3")
    suspend fun getLieagues(
        @Query("action") action: String,
        @Query("country_id") countryId: String,
        @Query("APIkey") apiKey:String
    ):List<ResponseLigaItem>

    @GET("apiV3")
    suspend fun getTeam(
        @Query("action") action: String,
        @Query("league_id") leagueId:String,
        @Query("APIkey") apiKey:String
    ):List<ResponseTeamItem>
}