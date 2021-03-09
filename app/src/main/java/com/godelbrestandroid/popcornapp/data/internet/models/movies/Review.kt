package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.godelbrestandroid.popcornapp.data.db.entities.movie.MovieDb
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Review(
    @Id var reviewId: Long = 0,//DB ONLY
    var author: String = "",
    var content: String = "",
    var id: String = "",
    var url: String = "",
    var page: Int = 0
) {
    lateinit var movieId: ToOne<MovieDb>
}