package com.example.russiansport.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.russiansport.R
import com.example.russiansport.databinding.ItemMatchBinding
import com.example.russiansport.domain.pojo.MatchUnit
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class AdapterMatch(var list: ArrayList<MatchUnit>) : RecyclerView.Adapter<AdapterMatch.ViewHolderMatch>(), Filterable {
    inner class ViewHolderMatch(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemMatchBinding.bind(itemView)
        val image1 = binding.imageMatchTeam1
        val image2 = binding.imageMatchTeam2
        val name1 = binding.textViewMatchName1
        val name2 = binding.textViewMatchName2
        val result = binding.textViewMatchResult
        val sport = binding.textViewMatchSport
        val date = binding.textViewMatchDate
        val tournament = binding.textViewMatchTournament
    }

    var filteredList = listOf<MatchUnit>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMatch {
        return ViewHolderMatch(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderMatch, position: Int) {
        val item = filteredList[position]
        with(holder){
            Picasso.get().load(item.image1).into(image1)
            Picasso.get().load(item.image2).into(image2)
            name1.text = item.team1
            name2.text = item.team2
            result.text = item.result
            sport.text = item.sport
            date.text = item.date
            tournament.text = item.tournament
        }
    }

    override fun getItemCount() = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()){
                    filteredList = list
                }else{
                    val result = ArrayList<MatchUnit>()
                    for (item in filteredList){
                        if (item.sport.lowercase(Locale.ROOT)
                                .trim()
                                .contains(charSearch.lowercase(Locale.ROOT))){
                            result.add(item)
                        }
                    }
                    filteredList = result
                }
                val filterResult = FilterResults()
                filterResult.values = filteredList
                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredList = p1?.values as ArrayList<MatchUnit>
                notifyDataSetChanged()
            }

        }
    }
}