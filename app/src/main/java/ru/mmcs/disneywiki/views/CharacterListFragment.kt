package ru.mmcs.disneywiki.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.mmcs.disneywiki.R
import ru.mmcs.disneywiki.databinding.FragmentListBinding
import ru.mmcs.disneywiki.viemodels.CharacterListViewModel

class CharacterListFragment : Fragment() {
    private lateinit var viewModel: CharacterListViewModel

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        _binding?.lifecycleOwner = this
        viewModel = CharacterListViewModel(_binding)
        _binding?.viewModel = viewModel
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if(uiState.navigationTargetId != null){
                        val args = Bundle()
                        args.putInt("character_id", uiState.navigationTargetId)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, args)
                        viewModel.onDetailsFragmentOpened()
                    }
                    if(uiState.errorMessage != null){
                        Toast.makeText(context,uiState.errorMessage,Toast.LENGTH_SHORT).show()
                        viewModel.onErrorMessageShown()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}