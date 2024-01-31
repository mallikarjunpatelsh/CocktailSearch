package com.wise.cocktail.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.wise.cocktail.R
import com.wise.cocktail.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    /**
     * Set loading state
     * hides if state is false and shows if state is true - progess bar
     * @param state
     */
    fun setLoadingState(state: Boolean) {
        if (::binding.isInitialized)
            binding.appProgressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}