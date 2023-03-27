package com.main.newsapplication.model.repository.db

import androidx.room.TypeConverters
import javax.xml.transform.Source

class Converters {
    @TypeConverters
    fun  fromSource(source: com.main.newsapplication.model.Source):String?{
        return source.name
    }
    @TypeConverters
    fun toSource(name:String):com.main.newsapplication.model.Source{
        return com.main.newsapplication.model.Source(name,name)
    }
}