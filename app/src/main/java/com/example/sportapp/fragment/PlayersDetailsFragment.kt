package com.example.sportapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.liga.Player
import com.example.sportapp.R
import com.example.sportapp.adapter.PlayerAdapter
import com.example.sportapp.databinding.FragmentPlayersDetailsBinding
import com.example.sportapp.viewmodel.SportViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PlayersDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPlayersDetailsBinding
    private val viewModel by viewModels<SportViewModel>()
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        val teamName = arguments?.getString("teamName")
        teamName?.let {
            viewModel.loadPlayer(it)
        }
    }

    private fun initView() {
        adapter = PlayerAdapter() // Создаем адаптер
        binding.rvCountry.adapter = adapter

        // Используем collect для наблюдения за StateFlow
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.country.collect { state ->
                // Проверяем, что состояние содержит список игроков (state.team)
                if (!state.team.isNullOrEmpty()) {
                    val responseTeamItem = state.team[0] // Первый элемент (возможно, это нужно будет адаптировать)
                    val players = responseTeamItem.players // Список игроков из ResponseTeamItem
                    adapter.submitList(players) // Передаем список игроков в адаптер
                }
            }
        }
    }
}