package com.example.russiansport.presentation.ui.tournaments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.R
import com.example.russiansport.databinding.FragmentHockeyBinding
import com.example.russiansport.presentation.adapters.AdapterHockey


class HockeyFragment : Fragment() {

    private lateinit var binding : FragmentHockeyBinding
    private lateinit var adapter: AdapterHockey
    private lateinit var viewModel:ViewModelTournament

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ViewModelTournament::class.java]
        adapter = AdapterHockey()
        binding = FragmentHockeyBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvHockey = binding.rvHockey
        viewModel.tournament.observe(viewLifecycleOwner,{
            val hockey = it[0].hockey
            adapter.list = hockey
            rvHockey.adapter = adapter
        })
    }
}