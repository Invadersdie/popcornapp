package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.godelbrestandroid.popcornapp.data.db.entities.movie.MovieImagesDb
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Poster(
    @Id var dbId: Long = 0,
    val aspect_ratio: Double = 0.0,
    val file_path: String = "",
    val height: Int = 0,
    val iso_639_1: String? = null,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val width: Int = 0
) {
    lateinit var movieImages: ToOne<MovieImagesDb>
}