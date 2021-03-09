package com.godelbrestandroid.popcornapp.data.models

import com.godelbrestandroid.popcornapp.data.internet.models.MediaType

data class ShortInfo(
    val id: Int,
    val dataType: MediaType,
    val title: String,
    val posterUrl: String?,
    val addInfo: String
)