package com.example.russiansport.data.database.mappers

import com.example.russiansport.data.database.models.MatchDbModel
import com.example.russiansport.data.network.dto.MatchDto
import com.example.russiansport.domain.pojo.MatchUnit

class MapperMatch {
    fun mapDbModelToEntity(dbModel:MatchDbModel) = MatchUnit(
        team1 = dbModel.team1,
        image1 = dbModel.image1,
        team2 = dbModel.team2,
        image2 = dbModel.image2,
        date = dbModel.date,
        result = dbModel.result,
        tournament = dbModel.tournament,
        sport = dbModel.sport
    )
    fun mapDtoToDbModel(dto: MatchDto) = MatchDbModel(
        id = 0,
        team1 = dto.team1?:"",
        image1 = dto.image1?:"",
        team2 = dto.team2?:"",
        image2 = dto.image2?:"",
        date = dto.date?:"",
        result = dto.result?:"",
        tournament = dto.tournament?:"",
        sport = dto.sport?:""
    )
}