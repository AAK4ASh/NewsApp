package com.main.newsapplication.model.utils

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.main.newsapplication.R
import com.main.newsapplication.model.Article

// Context is an Android-specific class that provides access to
// application-specific resources, such as system services, layouts, and strings

fun shareNews(context: Context?,article: Article){
    val intent = Intent().apply{
        action=Intent.ACTION_SEND// used to indicate that you want to send data or share data
        putExtra(Intent.EXTRA_TEXT,article.urlToImage)//putExtra to add value to intent
        putExtra(Intent.EXTRA_STREAM/*key value*/,article.urlToImage)
        putExtra(Intent.EXTRA_TITLE,article.title)
        type="/image"
    }
    context?.startActivity(Intent.createChooser(intent,"Share News On"))
}
fun getCircularDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 48f
        setTint(ContextCompat.getColor(context,R.color.teal_700))
        //context compat - This method takes a Context object and a resource ID, and returns the
        // corresponding color value as an integer.
        start()
    }
}//ic launcher changed
fun ImageView.loadImage(url:String,progressDrawable: CircularProgressDrawable){
    val options= RequestOptions()//RequestOptions class from the Glide,for editing
        .placeholder(progressDrawable)
    error(R.drawable.ic_launcher_background)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun loadImage(imageView: ImageView,url: String) {
    imageView.loadImage(url, getCircularDrawable(imageView.context))

}