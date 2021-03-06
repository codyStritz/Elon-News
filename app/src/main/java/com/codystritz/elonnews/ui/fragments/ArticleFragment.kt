package com.codystritz.elonnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.codystritz.elonnews.R
import com.codystritz.elonnews.databinding.FragmentArticleBinding


class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                allowFileAccess = false
            }
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl(article.url)
        }
    }


}