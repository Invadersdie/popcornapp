package com.godelbrestandroid.popcornapp.data.internet.models.movies

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Crew(
    var credit_id: String = "",
    var department: String = "",
    var gender: Int = 0,
    @Id(assignable = true) var id: Long = 0,
    var job: String = "",
    var name: String = "",
    var profile_path: String? = null
)