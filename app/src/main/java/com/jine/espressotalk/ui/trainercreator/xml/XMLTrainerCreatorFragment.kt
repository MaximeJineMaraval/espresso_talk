package com.jine.espressotalk.ui.trainercreator.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.jine.espressotalk.R
import com.jine.espressotalk.databinding.FragmentCreateTrainerBinding
import com.jine.espressotalk.ui.trainercreator.TrainerCreatorViewModel

class XMLTrainerCreatorFragment : Fragment() {

    private val viewModel: TrainerCreatorViewModel by viewModels(ownerProducer = { requireActivity() })
    private lateinit var binding: FragmentCreateTrainerBinding

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
        binding = FragmentCreateTrainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    private fun setupViews() {
        // Trainer name
        binding.trainerInputEditText.addTextChangedListener(
            { _, _, _, _ -> },
            { _, _, _, _ -> },
            { text ->
                viewModel.updateTrainerName(text?.toString() ?: "")
            })
        // Starter pokemon
        viewModel.pokemonName.observe(viewLifecycleOwner) {
            binding.starterInputEditText.setText(it)
        }
        binding.starterInputEditText.setOnFocusChangeListener { _, isFocus ->
            if(isFocus) {
                findNavController().navigate(R.id.XMLPokemonListFragment)
            }
        }
        // Create button
        viewModel.isCreateButtonEnabled.observe(viewLifecycleOwner) {
            binding.createButton.isEnabled = it
        }
    }

}