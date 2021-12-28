package com.example.russiansport.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.russiansport.R
import com.example.russiansport.databinding.ItemTournamentBinding
import com.example.russiansport.domain.pojo.HockeyUnit
import com.squareup.picasso.Picasso

class AdapterHockey : RecyclerView.Adapter<AdapterHockey.ViewHolderTournament>() {
    inner class ViewHolderTournament(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTournamentBinding.bind(itemView)
        val image = binding.imageViewTournament
        val country = binding.textViewTournamentCountry
        val category = binding.textViewTournamentCategory
        val date = binding.textViewTournamentDate
        val league = binding.textViewTournamentLeague
    }

    var list = listOf<HockeyUnit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTournament {
        return ViewHolderTournament(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tournament, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTournament, position: Int) {
        val item = list[position]
        with(holder){
            Picasso.get().load(item.image).into(image)
            country.text = item.country
            category.text = item.category
            date.text = item.dates
            league.text = item.league
        }
    }

    override fun getItemCount() = list.size
}