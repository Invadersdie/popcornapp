package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.db.entities.PersonDb
import com.godelbrestandroid.popcornapp.data.db.entities.PersonDb_
import com.godelbrestandroid.popcornapp.data.db.entities.toPerson
import com.godelbrestandroid.popcornapp.data.db.entities.toPersonDb
import com.godelbrestandroid.popcornapp.data.internet.models.KnownFor
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import com.godelbrestandroid.popcornapp.data.internet.server_api.PeopleApi
import com.godelbrestandroid.popcornapp.utils.NetworkUtils
import io.objectbox.BoxStore
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepositoryImpl @Inject constructor(db: BoxStore, networkUtils: NetworkUtils) :
    PeopleRepository, BaseRepository(networkUtils) {

    private val personBox = db.boxFor(PersonDb::class.java)
    private val knownForBox = db.boxFor(KnownFor::class.java)

    @Inject
    lateinit var peopleApi: PeopleApi

    override fun getPopularPeople(page: Int): Observable<List<Person>> {
        val query = personBox.query().equal(PersonDb_.page, page.toLong()).build()
        val dbRequest = RxQuery.observable(query).map { list -> list.map { it.toPerson() } }
        val netRequest = peopleApi.getPopularPeople(page)
            .doOnSuccess { saveListPeople(it.popularPeople, page) }
        return combine(dbRequest, netRequest)
    }

    private fun saveListPeople(people: List<Person>, page: Int) {
        for (person in people) {
            val personDb = person.toPersonDb(personBox, page)
            knownForBox.put(person.known_for)
            personDb.known_for.addAll(person.known_for)
            personBox.put(personDb)
        }
    }

    private fun updatePerson(person: Person) {
        val personDb = personBox.get(person.id.toLong())
        personDb.apply {
            birthday = person.birthday
            known_for_department = person.known_for_department
            deathday = person.deathday
            also_known_as = person.also_known_as
            gender = person.gender
            biography = person.biography
            place_of_birth = person.place_of_birth
            imdb_id = person.imdb_id
            homepage = person.homepage
        }
    }

    override fun getDetailsPerson(id: Int): Observable<Person> {
        val query = personBox.query().equal(PersonDb_.id, id.toLong()).build()
        val dbRequest =
            RxQuery.observable(query).flatMapIterable { list -> list.map { it.toPerson() } }
        val netRequest = peopleApi.getPersonDetails(id)
            .doOnSuccess { updatePerson(it) }
        return combine(dbRequest, netRequest)
    }
}