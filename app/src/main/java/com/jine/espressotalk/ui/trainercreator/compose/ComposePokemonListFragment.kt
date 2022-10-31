package com.jine.espressotalk.ui.trainercreator.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.jine.espressotalk.data.model.PokemonModel
import com.jine.espressotalk.tests.TestTags
import com.jine.espressotalk.ui.theme.PokemonComposeTheme
import com.jine.espressotalk.ui.trainercreator.PokemonListState
import com.jine.espressotalk.ui.trainercreator.PokemonListViewModel
import com.jine.espressotalk.ui.trainercreator.TrainerCreatorViewModel

class ComposePokemonListFragment : Fragment() {

    private val listViewModel: PokemonListViewModel by viewModels(ownerProducer = { requireActivity() })
    private val trainerCreatorViewModel: TrainerCreatorViewModel by viewModels(ownerProducer = { requireActivity() })

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

    @Composable
    private fun Screen() {
        val screenState by listViewModel.screenState.observeAsState()
        Box(modifier = Modifier.fillMaxSize()) {
            when (screenState) {
                is PokemonListState.Loading -> Loading()
                is PokemonListState.Success -> PokemonList(
                    pokemons = (screenState as PokemonListState.Success).pokemons
                )
                else -> {}
            }
        }
    }

    @Composable
    private fun Loading() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun PokemonList(
        pokemons: List<PokemonModel>
    ) {
        LazyVerticalGrid(
            modifier = Modifier.testTag(TestTags.PokemonList),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            content = {
                items(pokemons) {
                    PokemonItem(pokemon = it)
                }
            })
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun PokemonItem(pokemon: PokemonModel) {
        Card(
            onClick = {
                trainerCreatorViewModel.updatePokemonName(pokemon.name)
                findNavController().popBackStack()
            },
            modifier = Modifier
                .aspectRatio(4 / 3f)
                .padding(8.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            ConstraintLayout(modifier = Modifier.background(color = Color(pokemon.backgroundColor))) {
                val (name, details, image) = createRefs()

                Text(
                    text = pokemon.name,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(name) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top, margin = 16.dp)
                    })

                Text(
                    text = "${pokemon.type}\n${pokemon.heightAndWeight}",
                    color = Color.Black,
                    fontSize = 11.sp,

                    modifier = Modifier.constrainAs(details) {
                        top.linkTo(name.bottom, margin = 4.dp)
                        start.linkTo(name.start)
                        end.linkTo(image.start, margin = 8.dp)
                        width = Dimension.fillToConstraints
                    }
                )
                Image(
                    bitmap = pokemon.imageBitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(image) {
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                        })
            }
        }
    }
}