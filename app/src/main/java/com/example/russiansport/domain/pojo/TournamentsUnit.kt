package com.example.russiansport.domain.pojo

import androidx.annotation.Keep

@Keep
data class TournamentsUnit(
    val footbal: List<FootballUnit>,
    val hockey: List<HockeyUnit>
)