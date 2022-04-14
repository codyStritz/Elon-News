package com.codystritz.elonnews.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codystritz.elonnews.api.RetrofitInstance
import com.codystritz.elonnews.pagingsources.NewsPagingSource

class NewsRepository {
    fun getSearchNews(searchQuery: String) = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { NewsPagingSource(RetrofitInstance.api, searchQuery) }
    )

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}