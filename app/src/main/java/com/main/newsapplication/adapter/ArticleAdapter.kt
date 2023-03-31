package com.main.newsapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.main.newsapplication.databinding.ItemArticleBinding
import com.main.newsapplication.model.Article

class ArticleAdapter:RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    companion object{
        private val diffutilCallback= object :DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
return oldItem.title==oldItem.title            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
return newItem.title==newItem.title           }

        }
    }
    class ArticleViewHolder (var view:ItemArticleBinding):RecyclerView.ViewHolder(view.root)
val differ=AsyncListDiffer(this, diffutilCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view= DataBindingUtil.inflate<ItemArticleBinding>(inflater.R.layout.item_article,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
val article =differ.currentList[position]
    holder.view.article= article
    holder.ite
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}