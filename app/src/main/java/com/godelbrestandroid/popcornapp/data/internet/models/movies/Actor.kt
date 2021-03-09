package com.godelbrestandroid.popcornapp.data.internet.models.movies

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Actor(
    var cast_id: Int = 0,
    var character: String = "",
    var credit_id: String = "",
    var gender: Int = 0,
    @Id(assignable = true) var id: Long = 0,
    var name: String = "",
    var order: Int = 0,
    var profile_path: String? = null
)