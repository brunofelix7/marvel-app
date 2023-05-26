package dev.brunofelix.marvelapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.brunofelix.marvelapp.R
import dev.brunofelix.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureUI()
    }

    private fun configureUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureNavBar()
    }

    private fun configureNavBar() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigationView.apply {
            setupWithNavController(navHostFragment.navController)
        }
    }
}
