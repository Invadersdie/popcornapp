package com.godelbrestandroid.popcornapp.data.db.entities.movie

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Backdrop
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieImages
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Poster
import io.objectbox.Box
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class MovieImagesDb(
    @Id(assignable = true) var id: Long = 0,
    var movieId: Int
) {
    @Backlink
    lateinit var backdrops: ToMany<Backdrop>
    @Backlink
    lateinit var posters: ToMany<Poster>
}

fun MovieImagesDb.toMovieImages(): MovieImages {
    return MovieImages(this.backdrops, this.id.toInt(), this.posters)
}

fun MovieImages.toMovieImagesDb(box: Box<MovieImagesDb>, id: Int): MovieImagesDb {
    val movieImagesDb = MovieImagesDb(this.id.toLong(), movieId = id)
    box.attach(movieImagesDb)
    return movieImagesDb
}