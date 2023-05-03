package ru.mmcs.disneywiki.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ru.mmcs.disneywiki.R
import ru.mmcs.disneywiki.databinding.FragmentInfoBinding
import ru.mmcs.disneywiki.entities.DisneyCharacter
import ru.mmcs.disneywiki.repositories.CharacterRepository
import ru.mmcs.disneywiki.viemodels.CharacterActViewmodel
import ru.mmcs.disneywiki.viemodels.CharacterListViewModel

class CharacterInfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private lateinit var viewModel: CharacterActViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this,
            CharacterActViewmodel.Factory(arguments?.getParcelable("character")!!)
        ).get(CharacterActViewmodel::class.java)

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        setupBindingUi()
        return _binding!!.root
    }

    fun setupBindingUi(){
        _binding?.viewModel = viewModel
        Picasso.get().setIndicatorsEnabled(true)
        Picasso.get().load(viewModel.character.imageUrl).into(_binding?.ivPhoto)
        _binding?.tvName!!.text = viewModel.character.name
        _binding?.rvActs?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModel.characterActRvAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}