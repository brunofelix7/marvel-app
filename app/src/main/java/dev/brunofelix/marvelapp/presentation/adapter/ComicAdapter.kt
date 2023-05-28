package dev.brunofelix.marvelapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.brunofelix.marvelapp.databinding.ItemComicBinding
import dev.brunofelix.marvelapp.domain.model.Comic

class ComicAdapter : RecyclerView.Adapter<ComicViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

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
        holder.setIsRecyclable(false)
        holder.binding.apply {
            val imageUrl = comic.thumbnail?.path + comic.thumbnail?.extension

            comicName.text = comic.title
            comicDescription.text = comic.description
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .into(comicImage)
        }
    }
}

class ComicViewHolder(
    val binding: ItemComicBinding
) : RecyclerView.ViewHolder(binding.root)
