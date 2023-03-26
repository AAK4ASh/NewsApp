package com.main.newsapplication.model

data class Article(var id :Int?=null,var author:String,var content:String,var description:String,var source:Source,var title: String,var url:String,var urlToImage:String,var publishedAt:String)
