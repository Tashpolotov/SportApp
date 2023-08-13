    package com.example.sportapp.fragment

    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.viewModels
    import androidx.lifecycle.lifecycleScope
    import com.example.sportapp.R
    import com.example.sportapp.adapter.LigaAdapter
    import com.example.sportapp.databinding.FragmentLigaBinding
    import com.example.sportapp.viewmodel.SportViewModel
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.flow.collect

    @AndroidEntryPoint
    class LigaFragment : Fragment() {

        private lateinit var binding: FragmentLigaBinding
        private val adapter = LigaAdapter(this::onClick)

        private val viewModel by viewModels<SportViewModel>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentLigaBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initView()

            val selectedCountry = arguments?.getString("country")
            selectedCountry?.let { countryId ->
                viewModel.loadLeagues(countryId)
            }
        }

        private fun initView() {
            binding.rvCountry.adapter = adapter

            // Наблюдение за изменениями данных в StateFlow
            lifecycleScope.launchWhenCreated {
                viewModel.country.collect { state ->
                    // Обновление данных в адаптере
                    adapter.submitList(state.leagues)
                }
            }
        }

        private fun onClick(playerId:String) {
            val fragment = TeamFragment()
            val bundle  = Bundle().apply {
                putString("player", playerId)
            }
            fragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fr_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }