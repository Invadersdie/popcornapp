package com.godelbrestandroid.popcornapp.data.internet.models

import com.godelbrestandroid.popcornapp.data.db.entities.PersonDb
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter
import io.objectbox.relation.ToOne

@Entity
data class KnownFor(
    var adult: Boolean = true,
    var backdrop_path: String = "",
    @Convert(dbType = String::class, converter = ListIntConverter::class)
    var genre_ids: List<Int> = emptyList(),
    @Id(assignable = true) var id: Long = 0,
    @Convert(dbType = String::class, converter = MediaTypeConverter::class)
    var media_type: MediaType = MediaType.tv,
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String = "",
    var release_date: String = "",
    var title: String? = null,
    var video: Boolean = true,
    var vote_average: Double = 0.0,
    var vote_count: Int = 0
) {
    lateinit var person: ToOne<PersonDb>
}

class ListIntConverter : PropertyConverter<List<Int>, String> {
    private val separator = ";"
    override fun convertToDatabaseValue(entityProperty: List<Int>?): String {
        entityProperty?.let {
            return it.joinToString(separator)
        }
        return ""
    }

    override fun convertToEntityProperty(databaseValue: String?): List<Int> {
        databaseValue?.let {
            if (it.isNotEmpty()) {
                return it.split(separator).map { s -> s.toInt() }
            }
            return emptyList()
        }
        return emptyList()
    }
}