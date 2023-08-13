package com.example.domain.usecase

import com.example.domain.repository.SportRepository

class SportUseCase(private val repository: SportRepository) {

     suspend operator fun invoke(){
        repository.getCountry()
         repository.getLeagues(countryName = String())
         repository.getTeam(leagueId = String())
    }
}