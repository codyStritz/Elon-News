package com.codystritz.elonnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codystritz.elonnews.databinding.ItemArticlePreviewBinding
import com.codystritz.elonnews.models.Article

class ArticleAdapter
    : PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemBinding = ItemArticlePreviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { article ->
            holder.bind(article)
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(article: Article) {
                itemView.apply {
                    setOnClickListener {
                        onItemClickListener?.let { it(article) }
                    }
                }
                binding.apply {
                    Glide.with(itemView.context).load(article.urlToImage).into(ivArticleImage)
                    tvSource.text = article.source.name
                    tvTitle.text = article.title
                    tvDescription.text = article.description
                    tvPublishedAt.text = article.publishedAt
                }
            }
        }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}