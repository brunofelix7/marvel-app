package dev.brunofelix.marvelapp.feature_character.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.brunofelix.marvelapp.databinding.ItemCharacterBinding
import dev.brunofelix.marvelapp.feature_character.domain.model.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var characterList: List<Character>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private var onItemClickListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val root = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(root)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.binding.apply {
            val imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}"

            characterName.text = character.name
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .centerCrop()
                .into(characterImage)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(character)
            }
        }
    }

    fun setOnClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }
}

class CharacterViewHolder(
    val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root)