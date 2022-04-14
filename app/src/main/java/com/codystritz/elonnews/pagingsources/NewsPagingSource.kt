package com.codystritz.elonnews.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codystritz.elonnews.api.NewsApi
import com.codystritz.elonnews.models.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: FIRST_PAGE_INDEX
            val searchListResponse = newsApi.searchForNews(searchQuery, page).body()
            val articles = searchListResponse?.articles.orEmpty()
            val nextKey = if (articles.isEmpty()) null else page.plus(1)
            val prevKey = if (page == FIRST_PAGE_INDEX) null else page.minus(1)
            LoadResult.Page(
                data = articles,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}