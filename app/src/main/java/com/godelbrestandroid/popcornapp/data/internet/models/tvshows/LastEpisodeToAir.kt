package com.godelbrestandroid.popcornapp.data.internet.models.tvshows

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class LastEpisodeToAir(
    var air_date: String = "",
    var episode_number: Int = 0,
    @Id(assignable = true) var id: Long = 0,
    var name: String = "",
    var overview: String = "",
    var production_code: String = "",
    var season_number: Int = 0,
    var show_id: Int = 0,
    var still_path: String = "",
    var vote_average: Double = 0.0,
    var vote_count: Int = 0
)