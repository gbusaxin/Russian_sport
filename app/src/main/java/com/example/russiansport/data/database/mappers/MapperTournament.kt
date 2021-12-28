package com.example.russiansport.data.database.mappers

import com.example.russiansport.data.database.models.TournamentDbModel
import com.example.russiansport.data.network.dto.TournamentDto
import com.example.russiansport.domain.pojo.TournamentsUnit
import java.util.*

class MapperTournament {
    fun mapDtoToDbModel(dto:TournamentDto) = TournamentDbModel(
        id = 0,
        footbal = dto.footbal?:Collections.emptyList(),
        hockey = dto.hockey?:Collections.emptyList()
    )

    fun mapDbModelToEntity(dbModel: TournamentDbModel) = TournamentsUnit(
        footbal = dbModel.footbal,
        hockey = dbModel.hockey
    )
}