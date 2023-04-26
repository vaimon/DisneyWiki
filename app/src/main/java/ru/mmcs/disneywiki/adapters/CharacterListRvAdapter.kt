package ru.mmcs.disneywiki.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.mmcs.disneywiki.databinding.ItemCharacterBinding
import ru.mmcs.disneywiki.entities.DisneyCharacter

class CharacterListRvAdapter (
    var items: LiveData<List<DisneyCharacter?>?>,
    private val onItemInteractionListener: OnItemInteractionListener? = null
) : RecyclerView.Adapter<CharacterListRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.value!!.get(position)
        holder.binding.tvName.text = item?.name

        Picasso.get()
            .load(item?.imageUrl)
            .into(holder.binding.ivPhoto)

        holder.binding.root.setOnClickListener {
            onItemInteractionListener?.onItemClicked(item)
        }
    }

    override fun getItemCount(): Int = items.value!!.size

    inner class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemInteractionListener {
        fun onItemClicked(item: DisneyCharacter?)
    }

}