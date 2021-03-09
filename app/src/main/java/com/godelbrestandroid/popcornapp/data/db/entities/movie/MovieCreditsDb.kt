package com.godelbrestandroid.popcornapp.data.db.entities.movie

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Actor
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Crew
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieCredits
import io.objectbox.Box
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne


@Entity
data class MovieCreditsDb(
    @Id(assignable = true) var id: Long = 0
) {
    lateinit var movie: ToOne<MovieDb>
    lateinit var actorsList: ToMany<Actor>
    lateinit var crew: ToMany<Crew>
}

fun MovieCreditsDb.toMovieCredits(): MovieCredits {
    var actorList = this.actorsList.sortedBy { actor -> actor.order }
    if (actorList.size > 20) {
        actorList = actorList.subList(0, 20)
    }
    return MovieCredits(actorsList = actorList, crew = this.crew, id = this.id.toInt())
}

fun MovieCredits.toMovieCreditsDb(box: Box<MovieCreditsDb>): MovieCreditsDb {
    val movieCreditsDb = MovieCreditsDb(id = this.id.toLong())
    box.attach(movieCreditsDb)
    return movieCreditsDb
}