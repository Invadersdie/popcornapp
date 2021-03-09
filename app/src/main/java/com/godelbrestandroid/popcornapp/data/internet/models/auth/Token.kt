package com.godelbrestandroid.popcornapp.data.internet.models.auth

data class Token(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)