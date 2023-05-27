package dev.brunofelix.marvelapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.R
import dev.brunofelix.marvelapp.databinding.ActivityMainBinding
import timber.log.Timber

@AndroidEntryPoint
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

        Timber.d("bse_url= ${BuildConfig.BASE_URL}")
        Timber.d("public_key= ${BuildConfig.PUBLIC_KEY}")
        Timber.d("private_key= ${BuildConfig.PRIVATE_KEY}")
    }

    private fun configureNavBar() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigationView.apply {
            setupWithNavController(navHostFragment.navController)
        }
    }
}
