package com.main.newsapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.main.newsapplication.model.Article
import com.main.newsapplication.model.NewsResponse
import com.main.newsapplication.model.repository.NewsRepository
import com.main.newsapplication.model.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository): ViewModel() {
    private val breakingNews:MutableLiveData<Resource<NewsResponse>> =MutableLiveData()
    private var breakingPageNumber= 1
    private var breakingNewsResponse:NewsResponse?=null

    private val searchNews:MutableLiveData<Resource<NewsResponse>> =MutableLiveData()
    var searchPageNumber= 1
    var searchNewsResponse:NewsResponse?=null

    private lateinit var articles:LiveData<PagedList<Article>>
    init {
        getBreakingNews("in")
    }
    private fun getBreakingNews(countryCode:String)=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response= newsRepository.getBreakingNews(countryCode,breakingPageNumber)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
if (response.isSuccessful){
    response.body()?.let { resultResponse ->
        breakingPageNumber++
        if (breakingNewsResponse==null){
            breakingNewsResponse=resultResponse
        }else{
            val oldArticles= breakingNewsResponse?.articles
            val newArticle=resultResponse.articles
            oldArticles?.addAll(newArticle)
        }
        return Resource.Success(breakingNewsResponse?:resultResponse)
    }
}
        return Resource.Error(response.message())
    }
    fun getSearchedNews(queryString: String)= viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val searchNewsResponse=newsRepository.getSearchNews(queryString,searchPageNumber)
        searchNews.postValue(handleSearchNewsResponse(searchNewsResponse))
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>?{
if (response.isSuccessful){
    response.body()?.let { resultResponse->
        searchPageNumber++
        if (searchNewsResponse==null){
            searchNewsResponse=resultResponse
        }else
        {
            val oldArticle=searchNewsResponse?.articles
            val newArticles=resultResponse.articles
            oldArticle?.addAll(newArticles)
        }
return Resource.Success(searchNewsResponse?:resultResponse)

    }
}
        return Resource.Error(response.message())
    }
    fun insertArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun deleteARTICLE(article: Article)=viewModelScope.launch {
        newsRepository.delete(article)
    }
    fun getSavedArticles()=newsRepository.getAllArticles()

fun getBreakingNews():LiveData<PagedList<Article>>{
    return articles
}
    }