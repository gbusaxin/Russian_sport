package com.example.russiansport.presentation.ui.tournaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.databinding.BottomSheetTournamentDialogBinding
import com.example.russiansport.databinding.FragmentTournamentBinding

class TournamentFragment : Fragment() {

    private lateinit var binding: FragmentTournamentBinding
    private lateinit var viewModel:ViewModelTournament

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ViewModelTournament::class.java]
        binding = FragmentTournamentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}