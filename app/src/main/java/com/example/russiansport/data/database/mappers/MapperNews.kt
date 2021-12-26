package com.example.russiansport.data.database.mappers

import com.example.russiansport.data.database.models.NewsDbModel
import com.example.russiansport.data.network.dto.NewsDto
import com.example.russiansport.domain.pojo.NewsUnit

class MapperNews {

    fun mapDbModelToEntity(dbModel: NewsDbModel) = NewsUnit(
        title = dbModel.title,
        date = dbModel.date,
        image = dbModel.image,
        sport = dbModel.sport,
        shortDescription = dbModel.shortDescription,
        description = dbModel.description
    )

    fun mapDtoToDbModel(dto: NewsDto) = NewsDbModel(
        title = dto.title ?: "",
        date = dto.date ?: "",
        image = dto.image ?: "",
        sport = dto.sport ?: "",
        shortDescription = dto.shortDescription ?: "",
        description = dto.description ?: ""
    )

}