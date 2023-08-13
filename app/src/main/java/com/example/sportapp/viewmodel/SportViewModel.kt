package com.example.sportapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.SportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SportViewModel @Inject constructor(private val repisitory: SportRepository): ViewModel() {

    private val _country = MutableStateFlow(SportState())
    val country: StateFlow<SportState> = _country

    init {

        loadCountry()

    }

    private fun loadCountry() {
        viewModelScope.launch {
            val country = repisitory.getCountry()
            _country.value = _country.value.copy(country = country)
        }
    }

     fun loadLeagues(countryId:String){
        viewModelScope.launch {
            val leagues = repisitory.getLeagues(countryId)
            _country.value = _country.value.copy(leagues = leagues)
        }
    }
    fun loadPlayer(teamId:String){
        viewModelScope.launch {
            val team = repisitory.getTeam(teamId)
            _country.value = _country.value.copy(team = team)
        }
    }
}
