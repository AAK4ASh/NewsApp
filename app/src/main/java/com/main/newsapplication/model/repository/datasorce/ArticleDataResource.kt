package com.main.newsapplication.model.repository.datasorce

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.main.newsapplication.model.Article
import com.main.newsapplication.model.NewsResponse
import com.main.newsapplication.model.repository.RetrofitClient
import com.main.newsapplication.model.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticleDataResource( val scope:CoroutineScope): PageKeyedDataSource<Int, Article>(  ) {//for  breaking news
    val breakingNews:MutableLiveData<MutableList<Article>> = MutableLiveData()
    var breakingPageNumber=1
    var breakingNewsResponse:NewsResponse?=null
 //searching news
    val searchNews:MutableLiveData<MutableList<Article>> =MutableLiveData()
var searchNewsPageNumber=1
    var searchNewsResponse:NewsResponse?=null
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        scope.launch{
            try {
                val response= RetrofitClient.api.getBreakingNews("country",1,Constants.API_KEY)
                when {response.isSuccessful->{
                    response.body()?.articles?.let {
                        breakingNews.postValue(it)
                        callback.onResult(it,null,2)
                    }
                }
                }

            }catch (exception:Exception){
                Log.d("Data Source",exception.message.toString())
            }
        }
     }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
try {scope.launch {
    val response= RetrofitClient.api.getBreakingNews("in",params.requestedLoadSize,Constants.API_KEY)
    when{
        response.isSuccessful->{
            response.body()?.articles?.let { callback.onResult(it,params.key+1) }
        }
    }
}
  }catch (exception:Exception){Log.d("Data Source",exception.message.toString())}
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        TODO("Not yet implemented")
    }
}