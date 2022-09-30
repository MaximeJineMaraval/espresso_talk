package com.jine.espressotalk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.jine.espressotalk.databinding.ActivityMainBinding

// TODO: rajouter une petite animation entre les écrans (voir dans Vitamin)
// TODO: rajouter une barre de recherche
// TODO: (pas sur) rajouter une action sur le click d'un pokemon (une popup avec quelques détails ? autre ?)
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
                else -> ""
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(destination.id == R.id.pokemonListXMLFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).popBackStack()
        return true
    }
}