package com.godelbrestandroid.popcornapp.data.internet.models.tvshows

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Season(
    var air_date: String = "",
    var episode_count: Int = 0,
    @Id(assignable = true) var id: Long = 0,
    var name: String = "",
    var overview: String = "",
    var poster_path: String = "",
    var season_number: Int = 0
)