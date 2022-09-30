package com.jine.espressotalk.ui.pokemonlist.xml

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.transition.MaterialSharedAxis
import com.jine.espressotalk.R
import com.jine.espressotalk.databinding.FragmentPokemonListBinding
import com.jine.espressotalk.ui.pokemonlist.PokemonListState
import com.jine.espressotalk.ui.pokemonlist.PokemonListViewModel

class PokemonListXMLFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels(ownerProducer = { requireActivity() })
    private lateinit var binding: FragmentPokemonListBinding
    private val pokemonAdapter = PokemonAdapter(onPokemonClicked = { pokemonNumber ->
        viewModel.makeAsFavorite(pokemonNumber)
    })

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
        setupMenu()
        setupList()
        setupSearch()
        setupData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetFilters()
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.favorite -> {
                        menuItem.isChecked = menuItem.isChecked.not()
                        menuItem.setIcon(
                            if (menuItem.isChecked) {
                                R.drawable.ic_favorite_filled
                            } else {
                                R.drawable.ic_favorite_outlined
                            }
                        )
                        viewModel.showOnlyFavorite(menuItem.isChecked)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupList() {
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

    private fun setupSearch() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.performSearch(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.performSearch(newText ?: "")
                return true
            }
        })
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