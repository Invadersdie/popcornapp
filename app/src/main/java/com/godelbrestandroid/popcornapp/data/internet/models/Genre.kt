package com.godelbrestandroid.popcornapp.data.internet.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Genre(
    @Id(assignable = true) var id: Long = 0,
    var name: String
)