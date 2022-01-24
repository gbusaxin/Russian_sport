package com.example.russiansport.presentation.ui.tournaments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.R
import com.example.russiansport.databinding.FragmentHockeyBinding
import com.example.russiansport.presentation.adapters.AdapterHockey
import java.util.*


class HockeyFragment : Fragment() {

    private lateinit var binding : FragmentHockeyBinding
    private lateinit var adapter: AdapterHockey
    private val viewModel:ViewModelTournament by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = AdapterHockey()
        binding = FragmentHockeyBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvHockey = binding.rvHockey
        viewModel.getHockeyList.observe(viewLifecycleOwner,{
            Log.d("CHECK_DATA",it.toString())
            adapter.list = it
            rvHockey.adapter = adapter
        })
    }
}