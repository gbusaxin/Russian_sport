package com.example.russiansport.presentation.ui.tournaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.databinding.FragmentFootballBinding
import com.example.russiansport.presentation.adapters.AdapterFootball


class FootballFragment : Fragment() {

    private lateinit var binding: FragmentFootballBinding
    private lateinit var adapter: AdapterFootball
    private val viewModel: ViewModelTournament by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = AdapterFootball()
        binding = FragmentFootballBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvFootball = binding.rvFootball
        viewModel.getFootballList.observe(viewLifecycleOwner,{
            adapter.list = it
            rvFootball.adapter = adapter
        })
    }

}