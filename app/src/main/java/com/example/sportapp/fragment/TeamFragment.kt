package com.example.sportapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.liga.Player
import com.example.domain.model.liga.ResponseTeamItem
import com.example.sportapp.R
import com.example.sportapp.adapter.TeamAdapter
import com.example.sportapp.databinding.FragmentTeamBinding
import com.example.sportapp.viewmodel.SportViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private val adapter = TeamAdapter(this::onClick) // Используем обновленный конструктор адаптера
    private val viewModel by viewModels<SportViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.rvCountry.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.country.collect { state ->
                adapter.submitList(state.team) // Используем список country из состояния
            }
        }
    }

    private fun onClick(teamItem: ResponseTeamItem) { // Изменение параметра на ResponseTeamItem
        val fragment = PlayersDetailsFragment()
        val bundle = Bundle().apply {
            putString("teamName", teamItem.team_name) // Используем имя команды в аргументах
        }
        fragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fr_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}