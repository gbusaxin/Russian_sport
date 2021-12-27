package com.example.russiansport.presentation.ui.matches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.databinding.FragmentMatchesBinding
import com.example.russiansport.domain.pojo.MatchUnit
import com.example.russiansport.presentation.adapters.AdapterMatch

class MatchesFragment : Fragment() {

    private lateinit var binding: FragmentMatchesBinding
    private lateinit var adapter: AdapterMatch
    private lateinit var viewModel: ViewModelMatches

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ViewModelMatches::class.java]
        binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvMatches = binding.rvMatches
        val spinner = binding.spinnerSport
        viewModel.matchesList.observe(viewLifecycleOwner, {
            adapter = AdapterMatch(it as ArrayList<MatchUnit>)
            adapter.filteredList = it
            rvMatches.adapter = adapter
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("P_ZERO",p0?.selectedItem.toString())
                if (p2!=0) {
                    adapter.filter.filter(p0?.selectedItem.toString() as CharSequence)
                }
                Log.d("P_ONE",p1.toString())
                Log.d("P_TWO",p2.toString())
                Log.d("P_TREE",p3.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

}