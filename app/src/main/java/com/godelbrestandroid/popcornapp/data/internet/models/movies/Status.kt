package com.godelbrestandroid.popcornapp.data.internet.models.movies

import io.objectbox.converter.PropertyConverter

enum class Status(private val value: String) {
    Rumored("Rumored"),
    Planned("Planned"),
    In_Production("In Production"),
    Post_Production("Post Production"),
    Released("Released"),
    Canceled("Canceled");

    companion object {
        fun toStatus(text: String?): Status {
            return when (text) {
                Rumored.value -> Rumored
                Planned.value -> Planned
                In_Production.value -> In_Production
                Post_Production.value -> Rumored
                Released.value -> Released
                Canceled.value -> Canceled
                else -> Canceled
            }
        }
    }

    override fun toString(): String {
        return value
    }
}

class StatusConverter : PropertyConverter<Status?, String> {
    override fun convertToDatabaseValue(entityProperty: Status?): String {
        return entityProperty.toString()
    }

    override fun convertToEntityProperty(databaseValue: String?): Status {
        return Status.toStatus(databaseValue)
    }

}