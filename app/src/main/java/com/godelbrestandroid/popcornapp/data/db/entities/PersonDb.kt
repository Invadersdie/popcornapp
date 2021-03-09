package com.godelbrestandroid.popcornapp.data.db.entities

import com.godelbrestandroid.popcornapp.data.internet.models.KnownFor
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import io.objectbox.Box
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter
import io.objectbox.relation.ToMany

@Entity
data class PersonDb(
    var adult: Boolean = true,
    var biography: String = "",
    var birthday: String? = null,
    var deathday: String? = null,
    @Convert(dbType = String::class, converter = ListStringConverter::class)
    var also_known_as: List<String> = emptyList(),
    var gender: Int = 0,
    var homepage: String? = null,
    @Id(assignable = true) var id: Long = 0,
    var imdb_id: String = "",
    var known_for_department: String = "",
    var name: String = "",
    var place_of_birth: String? = null,
    var popularity: Double = 0.0,
    var profile_path: String? = null,
    var page: Int = 0
) {
    lateinit var known_for: ToMany<KnownFor>
}

fun Person.toPersonDb(box: Box<PersonDb>, page: Int): PersonDb {
    return PersonDb(
        adult = this.adult,
        id = this.id.toLong(),
        name = this.name,
        popularity = this.popularity,
        profile_path = this.profile_path,
        page = page
    ).apply { box.attach(this) }
}

fun PersonDb.toPerson(): Person {
    return Person(
        adult = this.adult,
        also_known_as = this.also_known_as,
        known_for = this.known_for,
        biography = this.biography,
        profile_path = this.profile_path,
        place_of_birth = this.place_of_birth,
        known_for_department = this.known_for_department,
        imdb_id = this.imdb_id,
        popularity = this.popularity,
        name = this.name,
        id = this.id.toInt(),
        homepage = this.homepage,
        birthday = this.birthday,
        deathday = this.deathday,
        gender = this.gender
    )
}

class ListStringConverter : PropertyConverter<List<String>, String> {
    private val separator = ";"
    override fun convertToDatabaseValue(entityProperty: List<String>?): String {
        entityProperty?.let {
            return it.joinToString(separator)
        }
        return ""
    }

    override fun convertToEntityProperty(databaseValue: String?): List<String> {
        databaseValue?.let {
            if (it.isNotEmpty()) {
                return it.split(separator)
            }
            return emptyList()
        }
        return emptyList()
    }
}