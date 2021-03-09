package com.godelbrestandroid.popcornapp.data.internet.server_api

import com.godelbrestandroid.popcornapp.data.internet.models.people.PageOfPeople
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {

    @GET("person/{person_id}")
    fun getPersonDetails(@Path("person_id") personId: Int): Single<Person>

    @GET("person/popular")
    fun getPopularPeople(@Query("page") pageToQuery: Int = 1): Single<PageOfPeople>
}