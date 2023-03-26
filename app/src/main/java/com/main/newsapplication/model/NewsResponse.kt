package com.main.newsapplication.model

data class NewsResponse(var articles:MutableList<Article>,val status:String,var totalResults:Int)
