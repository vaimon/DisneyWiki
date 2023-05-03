package ru.mmcs.disneywiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.mmcs.disneywiki.databinding.ItemActBinding
import ru.mmcs.disneywiki.databinding.ItemCharacterBinding
import ru.mmcs.disneywiki.entities.DisneyCharacter

class CharacterActRvAdapter (
    val items: List<Pair<DisneyCharacter.ActingType,String>>
) : RecyclerView.Adapter<CharacterActRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemActBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.binding.tvName.text = item.second
        holder.binding.tvType.text = item.first.description
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ItemActBinding) :
        RecyclerView.ViewHolder(binding.root)

}