package dev.brunofelix.marvelapp.feature_character.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.brunofelix.marvelapp.R
import dev.brunofelix.marvelapp.core.extension.loadImage
import dev.brunofelix.marvelapp.databinding.ItemComicBinding
import dev.brunofelix.marvelapp.feature_character.domain.model.Comic

class ComicAdapter : RecyclerView.Adapter<ComicViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var comicList: List<Comic>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val root = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(root)
    }

    override fun getItemCount() = comicList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = differ.currentList[position]
        holder.binding.apply {
            val imageUrl = "${comic.thumbnail?.path}.${comic.thumbnail?.extension}"
            val context = holder.itemView.context

            comicName.text = comic.title
            comicImage.loadImage(imageUrl)

            if (comic.description.isEmpty()) {
                comicDescription.text = context.resources.getString(R.string.empty_description)
            } else {
                comicDescription.text = comic.description
            }
        }
    }
}

class ComicViewHolder(
    val binding: ItemComicBinding
) : RecyclerView.ViewHolder(binding.root)
