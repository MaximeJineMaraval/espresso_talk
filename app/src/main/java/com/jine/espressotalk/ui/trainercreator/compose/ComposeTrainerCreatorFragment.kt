package com.jine.espressotalk.ui.trainercreator.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.jine.espressotalk.R
import com.jine.espressotalk.ui.theme.PokemonComposeTheme
import com.jine.espressotalk.ui.trainercreator.TrainerCreatorViewModel

class ComposeTrainerCreatorFragment : Fragment() {

    private val viewModel: TrainerCreatorViewModel by viewModels(ownerProducer = { requireActivity() })

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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    @Composable
    private fun Screen() {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create your trainer",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))
            TrainerNameField()
            Spacer(modifier = Modifier.height(32.dp))
            StarterPokemonField()
            Spacer(modifier = Modifier.height(32.dp))
            CreateButton()
        }
    }

    @Composable
    private fun TrainerNameField() {
        val trainerName by viewModel.trainerName.observeAsState()
        TextField(
            value = trainerName ?: "",
            onValueChange = {
                viewModel.updateTrainerName(it)
            },
            placeholder = { Text("Trainer name") },
        )
    }

    @Composable
    private fun StarterPokemonField() {
        val pokemonName by viewModel.pokemonName.observeAsState()
        TextField(
            value = pokemonName ?: "",
            onValueChange = {},
            placeholder = { Text("Starter pokemon") },
            modifier = Modifier.onFocusChanged {
                if (it.isFocused) {
                    findNavController().navigate(R.id.ComposePokemonListFragment)
                }
            },
        )
    }

    @Composable
    private fun CreateButton() {
        val isButtonEnabled by viewModel.isCreateButtonEnabled.observeAsState()
        Button(onClick = {}, enabled = isButtonEnabled ?: false) {
            Text(text = "CREATE TRAINER")
        }
    }

}