package com.codystritz.elonnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codystritz.elonnews.R
import com.codystritz.elonnews.adapters.ArticleAdapter
import com.codystritz.elonnews.databinding.FragmentTeslaBinding
import com.codystritz.elonnews.repository.NewsRepository
import com.codystritz.elonnews.ui.viewmodels.NewsViewModel
import com.codystritz.elonnews.ui.viewmodels.NewsViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TeslaFragment : Fragment() {

    private var _binding: FragmentTeslaBinding? = null
    private val binding get() = _binding!!
    private val viewModel : NewsViewModel by activityViewModels {
        NewsViewModelFactory(NewsRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeslaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val articleAdapter = ArticleAdapter()
        binding.rvTesla.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        articleAdapter.setOnItemClickListener {
            val action = TeslaFragmentDirections.actionTeslaFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNews("Tesla").collectLatest { articleAdapter.submitData(it) }
        }
    }


}