package com.example.projeto.projectmarvel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto.projectmarvel.data.model.ResultSeries
import com.example.projeto.projectmarvel.databinding.ItemComicsBinding
import com.squareup.picasso.Picasso

class AdapterSeries : RecyclerView.Adapter<AdapterSeries.Holder>(), AdapterContract {

    lateinit var onItemClickListener: (ResultSeries) -> Unit
    private var seriesList: MutableList<ResultSeries> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(seriesList[position])
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    fun updateItemsHome(itemList: List<ResultSeries>) {
        seriesList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun replaceItems(items: List<*>) {
        this.seriesList = items.filterIsInstance<ResultSeries>() as MutableList<ResultSeries>
        notifyDataSetChanged()
    }

    class Holder(
        private val binding: ItemComicsBinding,
        private val onItemClickListener: (ResultSeries) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var content: ResultSeries

        fun bind(content: ResultSeries) {
            val imgUrl = "${content.thumbnail.path}.${content.thumbnail.extension}"

            Picasso.get().load(imgUrl).resize(800, 0).into(binding.ivPoster)

            binding.tvTitle.text = content.title

            binding.root.setOnClickListener {
                onItemClickListener.invoke(content)
            }

        }

    }

}