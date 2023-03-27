package com.main.newsapplication.model.utils

//The T can represent any type, such as String, Int, or even a custom data class
sealed class Resource<T>(val data:T?=null,val message:String?=null){
    // nested subclasses
    class Success<T>(data: T?):Resource<T>(data)
    class Error<T>(data: T?,message: String?):Resource<T>(data,message)
    class Loading<T>:Resource<T>()
}
