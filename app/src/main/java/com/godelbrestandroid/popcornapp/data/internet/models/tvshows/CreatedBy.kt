package com.godelbrestandroid.popcornapp.data.internet.models.tvshows

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class CreatedBy(
    var credit_id: String = "",
    var gender: Int = 0,
    @Id(assignable = true) var id: Long = 0,
    var name: String = "",
    var profile_path: String = ""
)