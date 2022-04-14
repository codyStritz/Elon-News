package com.codystritz.elonnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codystritz.elonnews.R
import com.codystritz.elonnews.adapters.ArticleAdapter
import com.codystritz.elonnews.databinding.FragmentSpaceXBinding
import com.codystritz.elonnews.ui.viewmodels.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SpaceXFragment : Fragment() {

    private var _binding: FragmentSpaceXBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSpaceXBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        val articleAdapter = ArticleAdapter()
        binding.rvSpaceX.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        //Todo: implement - navigate to article fragment
        articleAdapter.setOnItemClickListener {}

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNews("SpaceX").collectLatest { articleAdapter.submitData(it) }
        }
    }


}