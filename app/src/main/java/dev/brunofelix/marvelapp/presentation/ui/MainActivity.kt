package dev.brunofelix.marvelapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.brunofelix.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureUI()
    }

    private fun configureUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
