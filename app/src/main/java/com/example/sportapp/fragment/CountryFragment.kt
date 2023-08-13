    package com.example.sportapp.fragment

    import android.os.Bundle
    import android.util.Log
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.viewModels
    import androidx.lifecycle.lifecycleScope
    import com.example.domain.model.SportResponseItem
    import com.example.sportapp.R
    import com.example.sportapp.adapter.CountryAdapter
    import com.example.sportapp.databinding.FragmentCountryBinding
    import com.example.sportapp.viewmodel.SportViewModel
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.flow.collect

    @AndroidEntryPoint
    class CountryFragment : Fragment() {

        private lateinit var binding:FragmentCountryBinding
        private val adapter = CountryAdapter(this::onClick)

        private val viewModel by viewModels<SportViewModel>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentCountryBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initView()
            getSport()
        }

        private fun initView() {
            binding.rvCountry.adapter = adapter
        }


        private fun getSport() {
            Log.e("CountryFragment", "setObservers: Observers are being set up")
            lifecycleScope.launchWhenCreated {
                viewModel.country.collect{
                        adapter.submitList(it.country)
                    }
            }

        }

        private fun onClick(countryId: String) {
            val fragment = LigaFragment()
            val bundle = Bundle().apply {
                putString("country", countryId)
            }
            fragment.arguments = bundle

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fr_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }