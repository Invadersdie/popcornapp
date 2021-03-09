package com.godelbrestandroid.popcornapp.data.internet.models.movies

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class SpokenLanguage(
    @Id var id: Long = 0,//DB only value
    var iso_639_1: String = "",
    var name: String = ""
)
