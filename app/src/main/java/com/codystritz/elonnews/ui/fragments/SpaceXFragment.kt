package com.codystritz.elonnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codystritz.elonnews.R
import com.codystritz.elonnews.databinding.FragmentSpaceXBinding


class SpaceXFragment : Fragment() {

    private var _binding: FragmentSpaceXBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSpaceXBinding.inflate(inflater, container, false)
        return binding.root
    }


}