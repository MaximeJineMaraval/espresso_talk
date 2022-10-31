package com.jine.espressotalk.ui.pokemonlist.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialSharedAxis
import com.jine.espressotalk.databinding.FragmentPokemonListBinding
import com.jine.espressotalk.ui.extensions.closeKeyboardOnScroll
import com.jine.espressotalk.ui.pokemonlist.PokemonListState
import com.jine.espressotalk.ui.pokemonlist.PokemonListViewModel

class PokemonListXMLFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels(ownerProducer = { requireActivity() })
    private lateinit var binding: FragmentPokemonListBinding
    private val pokemonAdapter = PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

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
        setupList()
        setupData()
    }

    private fun setupList() {
        binding.list.apply {
            layoutManager = GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false)
            adapter = pokemonAdapter
            closeKeyboardOnScroll()
        }
    }

    private fun setupData() {
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
}