package com.example.projeto.projectmarvel.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projeto.projectmarvel.data.model.ResultsMarvel
import com.example.projeto.projectmarvel.databinding.ItemCharactersBinding
import com.squareup.picasso.Picasso

class AdapterHome (private val context: Context) : RecyclerView.Adapter<AdapterHome.Holder>(),AdapterContract {

    lateinit var onItemClickListener: (ResultsMarvel) -> Unit
    private var characterList : MutableList<ResultsMarvel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(characterList[position])
//        val list = characterList[position]
//        val imgUrl = "${list.thumbnail.path}.${list.thumbnail.extension}"
//
//        Glide.with(context).load(imgUrl).into()
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateItemsHome(itemList: List<ResultsMarvel>) {
        characterList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun replaceItems(items : List<*>){
        this.characterList = items.filterIsInstance<ResultsMarvel>() as MutableList<ResultsMarvel>
        notifyDataSetChanged()
    }

    class Holder(
        private val binding: ItemCharactersBinding,
        private val onItemClickListener: (ResultsMarvel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var content: ResultsMarvel

        fun bind(content: ResultsMarvel) {
            val imgUrl = "${content.thumbnail.path}.${content.thumbnail.extension}"

            Picasso.get().load(imgUrl).resize(800,0).into(binding.ivPoster)
            binding.tvTitle.text = content.name

            binding.root.setOnClickListener {
                onItemClickListener.invoke(content)
            }

        }

    }

}