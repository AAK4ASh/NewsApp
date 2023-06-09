package com.main.newsapplication.model.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.main.newsapplication.model.Article

@Database(
    entities=[Article::class], version = 3
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase (){
    abstract fun getArticleDoa():ArticleDAO
    companion object{
        @Volatile
        private var articleDbInstance:ArticleDatabase?=null
        private var LOCK =Any()
        operator fun invoke(context: Context)= articleDbInstance?: synchronized(LOCK){
           articleDbInstance?:createDatabaseInstance(context).also {
                articleDbInstance=it
           }
        }
        private fun createDatabaseInstance(context: Context)=
            Room.databaseBuilder(context,ArticleDatabase::class.java,
            "articles_db.db").fallbackToDestructiveMigration().build()
    }
}