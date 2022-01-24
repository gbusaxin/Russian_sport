package com.example.russiansport.presentation.ui.tournaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.databinding.FragmentTournamentBinding
import com.example.russiansport.presentation.adapters.ViewPagerAdapter

class TournamentFragment : Fragment() {

    private lateinit var binding: FragmentTournamentBinding
    private lateinit var adapter: ViewPagerAdapter

    private lateinit var hockey : HockeyFragment
    private lateinit var football: FootballFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hockey = HockeyFragment()
        football = FootballFragment()
        adapter = ViewPagerAdapter(childFragmentManager)
        binding = FragmentTournamentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        adapter.addFragment(hockey,"Хоккей")
        adapter.addFragment(football,"Футбол")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


}