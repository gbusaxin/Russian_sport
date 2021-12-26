package com.example.russiansport.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.russiansport.R
import com.example.russiansport.databinding.ItemNewsBinding
import com.example.russiansport.domain.pojo.NewsUnit
import com.squareup.picasso.Picasso

class AdapterNews : RecyclerView.Adapter<AdapterNews.ViewHolderNews>() {
    inner class ViewHolderNews(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNewsBinding.bind(itemView)
        val image = binding.imageViewNews
        val title = binding.textViewNewsTitle
        val shortDesc = binding.textViewNewsShortDesc
        val date = binding.textViewNewsDate
    }

    var list = listOf<NewsUnit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onNewsClickListener:((NewsUnit) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNews {
        return ViewHolderNews(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderNews, position: Int) {
        val item = list[position]
        with(holder) {
            Picasso.get().load(item.image).into(image)
            title.text = item.title
            shortDesc.text = item.shortDescription
            date.text = item.date
        }
        holder.itemView.setOnClickListener {
          onNewsClickListener?.invoke(item)

        }
    }

    override fun getItemCount() = list.size
}