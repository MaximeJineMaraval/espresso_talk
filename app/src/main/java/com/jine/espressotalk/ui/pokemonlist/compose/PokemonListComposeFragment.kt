package com.jine.espressotalk.ui.pokemonlist.compose

import android.os.Bundle
import android.view.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.material.transition.MaterialSharedAxis
import com.jine.espressotalk.R
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.ui.pokemonlist.PokemonListState
import com.jine.espressotalk.ui.pokemonlist.PokemonListViewModel
import com.jine.espressotalk.ui.theme.PokemonComposeTheme
import com.jine.espressotalk.ui.theme.PokemonRedDark

class PokemonListComposeFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels(ownerProducer = { requireActivity() })

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
        return ComposeView(requireContext()).apply {
            isTransitionGroup = true
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
                PokemonComposeTheme {
                    Screen()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
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

    @Composable
    private fun Screen() {
        val screenState by viewModel.screenState.observeAsState()
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(performSearch = {
                viewModel.performSearch(it)
            })
            Divider()
            when (screenState) {
                is PokemonListState.Loading -> Loading()
                is PokemonListState.Success -> PokemonList(
                    pokemons = (screenState as PokemonListState.Success).pokemons,
                    onFavoriteClick = { viewModel.toggleFavorite(it) }
                )
                else -> {}
            }
        }
    }

    @Composable
    private fun SearchBar(performSearch: (text: String) -> Unit) {
        val searchState: MutableState<TextFieldValue> = remember {
            mutableStateOf(TextFieldValue(""))
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchState.value,
            onValueChange = {
                searchState.value = it
                performSearch(it.text)
            },
            placeholder = {
                Text(text = "Rechercher par nom ou type...")
            }, leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }, trailingIcon = {
                if (searchState.value.text.isNotBlank()) {
                    IconButton(onClick = {
                        searchState.value = TextFieldValue("")
                        performSearch("")
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )
    }

    @Composable
    private fun Loading() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun PokemonList(
        pokemons: List<PokemonModel>,
        onFavoriteClick: (pokemonNumber: Int) -> Unit
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(pokemons) {
                PokemonItem(pokemon = it, onFavoriteClick = {
                    onFavoriteClick(it.number)
                })
                Divider()
            }
        })
    }

    @Composable
    private fun PokemonItem(pokemon: PokemonModel, onFavoriteClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
            // Texts
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1.0f)
            ) {
                // Name
                Text(text = pokemon.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${pokemon.type}\n${pokemon.heightAndWeight}")
            }
            IconButton(onClick = { onFavoriteClick() }) {
                Image(
                    modifier = Modifier
                        .size(42.dp)
                        .padding(8.dp),
                    imageVector = if (pokemon.isFavorite()) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    colorFilter = ColorFilter.tint(if (pokemon.isFavorite()) PokemonRedDark else Color.Black),
                    contentDescription = null
                )
            }
        }
    }

}