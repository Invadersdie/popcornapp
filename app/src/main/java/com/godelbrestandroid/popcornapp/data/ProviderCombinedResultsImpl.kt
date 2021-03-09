package com.godelbrestandroid.popcornapp.data

import android.util.Log
import com.godelbrestandroid.popcornapp.data.models.HomeCombined
import com.godelbrestandroid.popcornapp.data.models.MovieDetailsCombined
import com.godelbrestandroid.popcornapp.data.models.MoviesCombined
import com.godelbrestandroid.popcornapp.data.models.TvShowsCombined
import com.godelbrestandroid.popcornapp.data.repositories.MoviesRepository
import com.godelbrestandroid.popcornapp.data.repositories.PeopleRepository
import com.godelbrestandroid.popcornapp.data.repositories.TvShowsRepository
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import io.reactivex.functions.Function5
import javax.inject.Inject

class ProviderCombinedResultsImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val peopleRepository: PeopleRepository,
    private val tvShowsRepository: TvShowsRepository
) : ProviderCombinedResults {

    override fun getMovieDetailsCombined(id: Int): Observable<MovieDetailsCombined> {
        return Observable.combineLatest(
            moviesRepository.getMovieDetails(id).doOnNext { Log.d("MovieDetails", "Details") },
            moviesRepository.getMovieImages(id).doOnNext { Log.d("MovieDetails", "Img") },
            moviesRepository.getMovieCredits(id).doOnNext { Log.d("MovieDetails", "Cred") },
            moviesRepository.getMovieReviews(id).doOnNext { Log.d("MovieDetails", "Rev") },
            moviesRepository.getMovieRecommendations(id).doOnNext { Log.d("MovieDetails", "Rec") },
            Function5 { movie, movieImages, credits, reviews, recommendations ->
                MovieDetailsCombined(
                    movie,
                    movieImages,
                    credits,
                    reviews,
                    recommendations
                )
            })
    }


    override fun getHomeCombinedResult(): Observable<HomeCombined> {
        return Observable.combineLatest(
            moviesRepository.getPopularMovies(),
            tvShowsRepository.downloadPopularTvShows(),
            peopleRepository.getPopularPeople(),
            Function3 { movies, tvShows, people ->
                HomeCombined(
                    movies,
                    tvShows,
                    people
                )
            })
    }

    override fun getMoviesCombined(): Observable<MoviesCombined> {
        return Observable.combineLatest(
            moviesRepository.getPopularMovies(),
            moviesRepository.getNowPlayingMovies(),
            moviesRepository.getTopRatedMovies(),
            moviesRepository.getUpcomingMovies(),
            Function4 { popular, nowPlaying, topRated, upcoming ->
                MoviesCombined(
                    popularMovies = popular,
                    nowPlayingMovies = nowPlaying,
                    topRatedMovies = topRated,
                    upcomingMovies = upcoming
                )
            })
    }

    override fun getTvShowsCombined(): Observable<TvShowsCombined> {
        return Observable.combineLatest(
            tvShowsRepository.downloadPopularTvShows(),
            tvShowsRepository.downloadAiringTvShows(),
            tvShowsRepository.downloadOnTheAirTvShows(),
            tvShowsRepository.downloadTopRatedTvShows(),
            Function4 { popular, onAiring, onTheAir, topRated ->
                TvShowsCombined(
                    popularTvShows = popular,
                    airingTvShows = onAiring,
                    onTheAirTvShows = onTheAir,
                    topRatedTvShows = topRated
                )
            }
        )
    }
}