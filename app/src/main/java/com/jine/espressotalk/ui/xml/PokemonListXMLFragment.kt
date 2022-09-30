package com.jine.espressotalk.ui.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.jine.espressotalk.databinding.FragmentPokemonListBinding
import com.jine.espressotalk.ui.PokemonListState
import com.jine.espressotalk.ui.PokemonListViewModel

class PokemonListXMLFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels(ownerProducer = { requireActivity() })
    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonAdapter = PokemonAdapter()
        setupList(pokemonAdapter)
        viewModel.screenState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is PokemonListState.Loading -> {
                    binding.list.visibility = View.GONE
                    binding.loading.visibility = View.VISIBLE
                }
                is PokemonListState.Success -> {
                    binding.list.visibility = View.VISIBLE
                    binding.loading.visibility = View.GONE
                    pokemonAdapter.pokemons = uiState.pokemons
                }
            }
        }
    }

    private fun setupList(pokemonAdapter: PokemonAdapter) {
        binding.list.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = pokemonAdapter
            addItemDecoration(
                MaterialDividerItemDecoration(
                    this.context,
                    MaterialDividerItemDecoration.VERTICAL
                )
            )
        }
    }
}