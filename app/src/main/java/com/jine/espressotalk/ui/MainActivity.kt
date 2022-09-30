package com.jine.espressotalk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.jine.espressotalk.R
import com.jine.espressotalk.databinding.ActivityMainBinding

// TODO: Ecrire les tests UI XML

// TODO: Cleaner le fichier gradle

// TODO: faire l'écran Compose
// TODO: Ecrire les tests UI Compose
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolBar)
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            title = when (destination.id) {
                R.id.mainFragment -> "Home"
                R.id.pokemonListXMLFragment -> "XML List"
                R.id.pokemonListComposeFragment -> "Compose List"
                else -> ""
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(
                destination.id == R.id.pokemonListXMLFragment
                        || destination.id == R.id.pokemonListComposeFragment
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).popBackStack()
        return true
    }
}