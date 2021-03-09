package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.godelbrestandroid.popcornapp.data.db.entities.movie.MovieImagesDb
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Backdrop(
    @Id var dbId: Long = 0,
    var aspect_ratio: Double = 0.0,
    var file_path: String = "",
    var height: Int = 0,
    var iso_639_1: String? = null,
    var vote_average: Double = 0.0,
    var vote_count: Int = 0,
    var width: Int = 0
) {
    //     var movieImages: ToOne<MovieImagesDb> = ToOne(this,MovieImagesDb_.backdrops)
    lateinit var movieImages: ToOne<MovieImagesDb>
}