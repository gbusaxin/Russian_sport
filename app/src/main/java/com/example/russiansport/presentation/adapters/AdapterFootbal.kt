package com.example.russiansport.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.russiansport.R
import com.example.russiansport.databinding.ItemTournamentBinding
import com.example.russiansport.domain.pojo.FootballUnit
import com.squareup.picasso.Picasso

class AdapterFootball : RecyclerView.Adapter<AdapterFootball.ViewHolderFootball>() {
    inner class ViewHolderFootball(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTournamentBinding.bind(itemView)
        val image = binding.imageViewTournament
        val country = binding.textViewTournamentCountry
        val category = binding.textViewTournamentCategory
        val date = binding.textViewTournamentDate
        val league = binding.textViewTournamentLeague
    }

    var list = listOf<FootballUnit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFootball {
        return ViewHolderFootball(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tournament, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderFootball, position: Int) {
        val item = list[position]
        with(holder) {
            Picasso.get().load(item.image).into(image)
            country.text = item.country
            category.text = item.category
            date.text = item.dates
            league.text = item.league
        }
    }

    override fun getItemCount() = list.size
}