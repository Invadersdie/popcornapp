package com.godelbrestandroid.popcornapp.ui

import com.godelbrestandroid.popcornapp.data.internet.models.MediaType

interface Clickable {
    fun onItemClick(itemType: MediaType, id: Int)
}
