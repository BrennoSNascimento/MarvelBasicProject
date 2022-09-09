package com.example.projeto.projectmarvel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto.projectmarvel.data.model.ComicsData
import com.example.projeto.projectmarvel.databinding.ItemComicsBinding
import com.squareup.picasso.Picasso

class AdapterComics() : RecyclerView.Adapter<AdapterComics.Holder>(), AdapterContract {

    lateinit var onItemClickListener: (ComicsData) -> Unit
    private var characterList: MutableList<ComicsData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateItemsHome(itemList: List<ComicsData>) {
        characterList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun replaceItems(items: List<*>) {
        this.characterList = items.filterIsInstance<ComicsData>() as MutableList<ComicsData>
        notifyDataSetChanged()
    }

    class Holder(
        private val binding: ItemComicsBinding,
        private val onItemClickListener: (ComicsData) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var content: ComicsData

        fun bind(content: ComicsData) {
            val imgUrl = "${content.thumbnail.path}.${content.thumbnail.extension}"

            Picasso.get().load(imgUrl).resize(800, 0).into(binding.ivPoster)

            binding.tvTitle.text = content.title

            binding.root.setOnClickListener {
                onItemClickListener.invoke(content)
            }

        }

    }

}