package com.godelbrestandroid.popcornapp.data.internet.models

import io.objectbox.converter.PropertyConverter

enum class MediaType {
    movie,
    tv,
    person;

    companion object {
        fun toMediaType(text: String?): MediaType {
            return when (text) {
                movie.toString() -> movie
                tv.toString() -> tv
                person.toString() -> person
                else -> movie
            }
        }
    }
}

class MediaTypeConverter : PropertyConverter<MediaType, String> {
    override fun convertToDatabaseValue(entityProperty: MediaType?): String {
        return entityProperty.toString()
    }

    override fun convertToEntityProperty(databaseValue: String?): MediaType {
        return MediaType.toMediaType(databaseValue)
    }

}