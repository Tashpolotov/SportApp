package com.example.sportapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.sportapp.R
import com.example.sportapp.databinding.ActivityMainBinding
import com.example.sportapp.fragment.CountryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val fragment = CountryFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fr_container, fragment)
            .addToBackStack(null)
            .commit()

    }
}