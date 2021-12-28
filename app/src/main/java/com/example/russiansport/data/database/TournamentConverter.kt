package com.example.russiansport.data.database

import androidx.room.TypeConverter
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class TournamentConverter {
    @TypeConverter
    fun toListHockeyFromJson(hockey:String):List<HockeyUnit>{
        if(hockey == null) return Collections.emptyList()
        val type: Type = object : TypeToken<List<HockeyUnit>>(){}.type
        return Gson().fromJson(hockey,type)
    }
    @TypeConverter
    fun fromJsonToListHockey(hockey:List<HockeyUnit>):String{
        return Gson().toJson(hockey)
    }

    @TypeConverter
    fun toListFootballFromJson(football:String):List<FootballUnit>{
        if(football == null) return Collections.emptyList()
        val type: Type = object : TypeToken<List<FootballUnit>>(){}.type
        return Gson().fromJson(football,type)
    }
    @TypeConverter
    fun fromJsonToListFootball(football:List<FootballUnit>):String{
        return Gson().toJson(football)
    }
}