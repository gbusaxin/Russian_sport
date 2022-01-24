package com.example.russiansport.data.database.mappers

import com.example.russiansport.data.database.models.FootballDbModel
import com.example.russiansport.data.database.models.HockeyDbModel
import com.example.russiansport.data.network.dto.FootballDto
import com.example.russiansport.data.network.dto.HockeyDto
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit

class MapperTournament {

    fun mapHockeyDtoToDbModel(dto: HockeyDto) = HockeyDbModel(
        league = dto.league ?: "",
        image = dto.image ?: "",
        country = dto.country ?: "",
        category = dto.category ?: "",
        dates = dto.dates ?: ""
    )

    fun mapHockeyDbModelToEntity(dbModel: HockeyDbModel) = HockeyUnit(
        league = dbModel.league,
        image = dbModel.image,
        country = dbModel.country,
        category = dbModel.category,
        dates = dbModel.dates
    )

    fun mapFootballDtoToDbModel(dto: FootballDto) = FootballDbModel(
        league = dto.league ?: "",
        image = dto.image ?: "",
        country = dto.country ?: "",
        category = dto.category ?: "",
        dates = dto.dates ?: ""
    )

    fun mapFootballDbModelToEntity(dbModel: FootballDbModel) = FootballUnit(
        league = dbModel.league,
        image = dbModel.image,
        country = dbModel.country,
        category = dbModel.category,
        dates = dbModel.dates
    )
}