package com.codystritz.elonnews.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codystritz.elonnews.models.Article
import com.codystritz.elonnews.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getNews(searchQuery: String) : Flow<PagingData<Article>> {
        return newsRepository
            .getSearchNews(searchQuery)
            .flow
            .cachedIn(viewModelScope)
    }
}

class NewsViewModelFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}