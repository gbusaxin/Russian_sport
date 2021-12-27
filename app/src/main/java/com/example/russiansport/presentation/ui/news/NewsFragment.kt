package com.example.russiansport.presentation.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.russiansport.databinding.FragmentNewsBinding
import com.example.russiansport.presentation.adapters.AdapterNews

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModelNews: ViewModelNews
    private lateinit var adapter: AdapterNews

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelNews = ViewModelProvider(this)[ViewModelNews::class.java]
        adapter = AdapterNews(parentFragmentManager)
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvNews = binding.rvNews
        viewModelNews.newsList.observe(viewLifecycleOwner, {
            adapter.list = it
            rvNews.adapter = adapter
        })
    }
}