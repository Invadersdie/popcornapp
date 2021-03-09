package com.godelbrestandroid.popcornapp.data.internet.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ProductionCompany(
    @Id(assignable = true) var id: Long = 0,
    var logo_path: String? = null,
    var name: String = "",
    var origin_country: String = ""
)