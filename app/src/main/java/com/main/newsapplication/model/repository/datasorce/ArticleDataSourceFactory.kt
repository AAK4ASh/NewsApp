package com.main.newsapplication.model.repository.datasorce

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.main.newsapplication.model.Article
import com.main.newsapplication.model.repository.datasorce.ArticleDataResource
import kotlinx.coroutines.CoroutineScope

class ArticleDataSourceFactory (private val scope: CoroutineScope):androidx.paging.DataSource.Factory<Int,Article>(){
private val articleDataSourceLiveData=MutableLiveData<ArticleDataResource>()
    override fun create(): DataSource<Int, Article> {
        val newArticleDataSource= ArticleDataResource(scope)
        articleDataSourceLiveData.postValue(newArticleDataSource)
        return newArticleDataSource

    }
}