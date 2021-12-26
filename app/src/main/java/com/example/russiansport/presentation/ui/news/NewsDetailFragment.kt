package com.example.russiansport.presentation.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.russiansport.R
import com.example.russiansport.databinding.FragmentNewsBinding
import com.example.russiansport.databinding.FragmentNewsDetailBinding
import com.example.russiansport.domain.pojo.NewsUnit


class NewsDetailFragment(news:NewsUnit) : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = binding.textViewDetailTitle
        val date = binding.textViewDetailDate
        val image = binding.imageViewDetailImage
        val shortDesc = binding.textViewDetailShortDesc
        val description = binding.textViewDetailDescription
    }

}