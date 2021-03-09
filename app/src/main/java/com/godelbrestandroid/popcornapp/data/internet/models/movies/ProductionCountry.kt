package com.godelbrestandroid.popcornapp.data.internet.models.movies

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ProductionCountry(
    @Id var id: Long = 0,//DB only value
    var iso_3166_1: String = "",
    var name: String = ""
)