package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import io.reactivex.Observable

interface PeopleRepository {
    fun getPopularPeople(page: Int = 1): Observable<List<Person>>
    fun getDetailsPerson(id: Int): Observable<Person>
}