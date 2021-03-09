package com.godelbrestandroid.popcornapp.data.mappers

import com.godelbrestandroid.popcornapp.data.internet.models.KnownFor
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Actor
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Movie
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieCredits
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import com.godelbrestandroid.popcornapp.data.internet.models.search.MultiSearchResult
import com.godelbrestandroid.popcornapp.data.internet.models.search.SearchResult
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import javax.inject.Inject

class ShortInfoMapperImpl @Inject constructor() : ShortInfoMapper {

    override fun movieCreditsToShortList(movieCredits: MovieCredits): List<ShortInfo> {
        return movieCredits.actorsList.map { actorToShortInfo(it) }
    }

    private fun actorToShortInfo(actor: Actor): ShortInfo {
        return ShortInfo(
            actor.id.toInt(),
            MediaType.person,
            actor.name,
            actor.profile_path,
            actor.character
        )
    }

    override fun multiSearchResultToShortInfoList(multiSearchResult: MultiSearchResult): List<ShortInfo> {
        return multiSearchResult.searchResults.map { searchResultToShortInfo(it) }
    }

    private fun searchResultToShortInfo(searchResult: SearchResult): ShortInfo {
        return when (searchResult.media_type) {
            MediaType.movie -> ShortInfo(
                searchResult.id,
                MediaType.movie,
                searchResult.title,
                searchResult.poster_path,
                searchResult.release_date
            )
            MediaType.tv -> ShortInfo(
                searchResult.id,
                MediaType.tv,
                searchResult.name,
                searchResult.poster_path,
                searchResult.first_air_date
            )
            MediaType.person -> ShortInfo(
                searchResult.id,
                MediaType.person,
                searchResult.name,
                searchResult.profile_path,
                knownForListToStringOfTitles(searchResult.known_for)
            )
        }
    }

    private fun movieToShortData(movie: Movie): ShortInfo {
        return ShortInfo(
            movie.id,
            MediaType.movie,
            movie.title,
            movie.poster_path,
            movie.release_date
        )
    }

    private fun tvShowToShortData(tvShow: TvShow): ShortInfo {
        return ShortInfo(
            id = tvShow.id,
            addInfo = "",
            posterUrl = tvShow.poster_path,
            title = tvShow.name,
            dataType = MediaType.tv
        )
    }

    private fun knownForListToStringOfTitles(knownForList: List<KnownFor>): String {
        val stringOfTitles = StringBuilder()
        for (knownFor in knownForList) {
            if (knownFor.title != null && knownFor.title != "null") {
                stringOfTitles.append(knownFor.title).append(",")
            }
        }
        if (stringOfTitles.isNotEmpty() && stringOfTitles.last() == ',') {
            stringOfTitles.setLength(stringOfTitles.length - 1)
        }
        return stringOfTitles.toString()
    }

    private fun personToShortData(person: Person): ShortInfo {
        return ShortInfo(
            person.id,
            MediaType.person,
            person.name,
            person.profile_path,
            knownForListToStringOfTitles(person.known_for)
        )
    }

    override fun pageOfMoviesToShortInfoList(listMovie: List<Movie>): List<ShortInfo> {
        return listMovie.map { movieToShortData(it) }
    }

    override fun pageOfMoviesWithRateToShortInfoList(listMovie: List<Movie>): List<ShortInfo> {
        return listMovie.map { movieWithRateToShortData(it) }
    }

    private fun movieWithRateToShortData(movie: Movie): ShortInfo {
        return ShortInfo(
            movie.id,
            MediaType.movie,
            movie.title,
            movie.poster_path,
            movie.vote_average.toString()
        )
    }

    override fun pageOfTvShowsToShortInfoList(listTvShows: List<TvShow>): List<ShortInfo> {
        return listTvShows.map { tvShowToShortData(it) }

    }

    override fun pageOfPeopleToShortInfoList(listPeople: List<Person>): List<ShortInfo> {
        return listPeople.map { personToShortData(it) }
    }
}