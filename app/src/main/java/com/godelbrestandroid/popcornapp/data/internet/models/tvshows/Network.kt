package com.godelbrestandroid.popcornapp.data.internet.models.tvshows

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Network(
    @Id(assignable = true) var id: Long = 0,
    var logo_path: String = "",
    var name: String = "",
    var origin_country: String = ""
)