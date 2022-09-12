package com.example.projeto.projectmarvel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto.projectmarvel.data.model.SavedCharacter
import com.example.projeto.projectmarvel.databinding.ItemComicsBinding
import com.example.projeto.projectmarvel.databinding.ItemFavoritedBinding
import com.squareup.picasso.Picasso

class AdapterFavorited : RecyclerView.Adapter<AdapterFavorited.Holder>(), AdapterContract {

    lateinit var onItemClickListener: (SavedCharacter) -> Unit
    private var seriesList: MutableList<SavedCharacter> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemFavoritedBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(seriesList[position])
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    fun updateItemsHome(itemList: List<SavedCharacter>) {
        seriesList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun replaceItems(items: List<*>) {
        this.seriesList = items.filterIsInstance<SavedCharacter>() as MutableList<SavedCharacter>
        notifyDataSetChanged()
    }

    class Holder(
        private val binding: ItemFavoritedBinding,
        private val onItemClickListener: (SavedCharacter) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var content: SavedCharacter

        fun bind(content: SavedCharacter) {
            val imgUrl = "${content.thumbnail.path}.${content.thumbnail.extension}"

            Picasso.get().load(imgUrl).resize(800, 0).into(binding.ivPoster)

            binding.tvTitle.text = content.name

            binding.icClose.setOnClickListener {
                onItemClickListener.invoke(content)
            }

        }

    }

}