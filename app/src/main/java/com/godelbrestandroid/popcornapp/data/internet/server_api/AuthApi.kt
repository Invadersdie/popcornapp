package com.godelbrestandroid.popcornapp.data.internet.server_api

import com.godelbrestandroid.popcornapp.data.internet.models.auth.Token
import io.reactivex.Single
import retrofit2.http.GET

interface AuthApi {

    @GET("/authentication/token/new/")
    fun getAuthTokenNew(): Single<Token>
}