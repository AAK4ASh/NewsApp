package com.main.newsapplication.model.repository

import com.main.newsapplication.model.Article
import com.main.newsapplication.model.repository.db.ArticleDatabase

class NewsRepository (val db:ArticleDatabase){
    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=
        RetrofitClient.api.getBreakingNews(countryCode,pageNumber)
    suspend fun getSearchNews(q:String,pageNumber:Int)=
        RetrofitClient.api.getSearchNews(q,pageNumber)
    suspend fun upsert(article: Article)=db.getArticleDoa().insert(article)
    suspend fun delete(article: Article)=db.getArticleDoa().deleteArticle(article)

    fun getAllArticles()=db.getArticleDoa().getArticles()
}