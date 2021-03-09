package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.db.entities.movie.*
import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.ProductionCompany
import com.godelbrestandroid.popcornapp.data.internet.models.movies.*
import com.godelbrestandroid.popcornapp.data.internet.server_api.MoviesApi
import com.godelbrestandroid.popcornapp.utils.NetworkUtils
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.query.OrderFlags.DESCENDING
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    db: BoxStore,
    networkUtils: NetworkUtils
) : MoviesRepository, BaseRepository(networkUtils) {

    @Inject
    lateinit var moviesApi: MoviesApi

    private val movieBox: Box<MovieDb> = db.boxFor(MovieDb::class.java)
    private val movieCreditsBox: Box<MovieCreditsDb> = db.boxFor(MovieCreditsDb::class.java)
    private val movieImagesBox: Box<MovieImagesDb> = db.boxFor(MovieImagesDb::class.java)
    private val backdropBox: Box<Backdrop> = db.boxFor(Backdrop::class.java)
    private val posterBox: Box<Poster> = db.boxFor(Poster::class.java)
    private val reviewBox: Box<Review> = db.boxFor(Review::class.java)
    private val genreBox: Box<Genre> = db.boxFor(Genre::class.java)


    private val actorBox: Box<Actor> = db.boxFor(Actor::class.java)
    private val crewBox: Box<Crew> = db.boxFor(Crew::class.java)


    private val productionCompanyBox: Box<ProductionCompany> =
        db.boxFor(ProductionCompany::class.java)
    private val productionCountryBox: Box<ProductionCountry> =
        db.boxFor(ProductionCountry::class.java)
    private val spokenLanguageBox: Box<SpokenLanguage> = db.boxFor(SpokenLanguage::class.java)


    override fun getMovieCredits(id: Int): Observable<MovieCredits> {
        val query = movieCreditsBox.query().equal(MovieCreditsDb_.movieId, id.toLong()).build()
        val dbRequest = RxQuery.observable(query).distinctUntilChanged()
            .concatMapIterable { list ->
                list.map {
                    it.toMovieCredits()
                }
            }
        val netRequest =
            moviesApi.getMovieCredits(id).doOnSuccess { saveMovieCredits(it, id) }
        return combine(dbRequest, netRequest)
    }

    override fun getMovieReviews(id: Int): Observable<List<Review>> {
        val query = reviewBox.query().equal(Review_.movieIdId, id.toLong()).build()
        val dbRequest = RxQuery.observable(query).distinctUntilChanged()
        val netRequest = moviesApi.getMovieReviews(id).doOnSuccess { saveMovieReviews(it, id) }
        return combineList(dbRequest, netRequest)
    }


    override fun getMovieRecommendations(id: Int): Observable<List<Movie>> {
        val queryBuilder = movieBox.query()
        queryBuilder.link(MovieDb_.recommendations).equal(MovieDb_.id, id.toLong())
        val query = queryBuilder.build()
        val dbRequest =
            RxQuery.observable(query).map { i -> i.map { it.toMovie() } }.distinctUntilChanged()
        val netRequest =
            moviesApi.getMovieRecommendations(id).doOnSuccess {
                saveMovies(it.moviesList, recommendedTo = id, type = MovieType.RECOMMENDED)
            }
        return combineList(dbRequest, netRequest)
    }

    override fun getPopularMovies(page: Int): Observable<List<Movie>> {
        val query = movieBox.query().equal(MovieDb_.popularPage, page.toLong())
            .order(MovieDb_.popularity, DESCENDING).build()
        val dbRequest =
            RxQuery.observable(query).map { i -> i.map { it.toMovie() } }.distinctUntilChanged()
        val netRequest = moviesApi.getPopularMovies(page).doOnSuccess {
            saveMovies(it.moviesList, it.page, MovieType.POPULAR)
        }
        return combineList(dbRequest, netRequest)
    }


    override fun getNowPlayingMovies(page: Int): Observable<List<Movie>> {
        val query = movieBox.query().equal(MovieDb_.nowPlayingPage, page.toLong()).build()
        val dbRequest =
            RxQuery.observable(query).map { i -> i.map { it.toMovie() } }.distinctUntilChanged()
        val netRequest = moviesApi.getNowPlayingMovies(page).doOnSuccess {
            saveMovies(it.moviesList, it.page, MovieType.NOW_PLAYING)
        }
        return combineList(dbRequest, netRequest)
    }

    override fun getTopRatedMovies(page: Int): Observable<List<Movie>> {
        val query = movieBox.query().equal(MovieDb_.topRatedPage, page.toLong()).build()
        val dbRequest =
            RxQuery.observable(query).map { i -> i.map { it.toMovie() } }.distinctUntilChanged()
        val netRequest = moviesApi.getTopRatedMovies(page).doOnSuccess {
            saveMovies(it.moviesList, it.page, MovieType.TOP_RATED)
        }
        return combineList(dbRequest, netRequest)
    }

    override fun getUpcomingMovies(page: Int): Observable<List<Movie>> {
        val query = movieBox.query().equal(MovieDb_.upcomingPage, page.toLong()).build()
        val dbRequest =
            RxQuery.observable(query).map { i -> i.map { it.toMovie() } }.distinctUntilChanged()
        val netRequest = moviesApi.getUpcomingMovies(page)
            .doOnSuccess { saveMovies(it.moviesList, it.page, MovieType.UPCOMING) }
        return combineList(dbRequest, netRequest)
    }

    override fun getMovieDetails(id: Int): Observable<Movie> {
        val query = movieBox.query().equal(MovieDb_.id, id.toLong()).build()
        val dbRequest = RxQuery.observable(query).concatMapIterable { i -> i.map { it.toMovie() } }
            .distinctUntilChanged()
        val netRequest = moviesApi.getMovieDetails(id).doOnSuccess { updateMovie(it) }
        return combine(dbRequest, netRequest)
    }

    override fun getMovieImages(id: Int): Observable<MovieImages> {
        val query = movieImagesBox.query().equal(MovieImagesDb_.movieId, id.toLong()).build()
        val dbRequest =
            RxQuery.observable(query).concatMapIterable { i -> i.map { it.toMovieImages() } }
                .distinctUntilChanged()
        val netRequest = moviesApi.getMovieImages(id).doOnSuccess { saveMovieImages(it, id) }
        return combine(dbRequest, netRequest)
    }

    enum class MovieType {
        POPULAR,
        TOP_RATED,
        NOW_PLAYING,
        UPCOMING,
        RECOMMENDED
    }

    private fun saveMovies(
        movies: List<Movie>,
        page: Int = 0,
        type: MovieType,
        recommendedTo: Int? = null
    ) {
        for (movie in movies) {
            val movieDb = movie.toMovieDb(movieBox)

            when (type) {
                MovieType.POPULAR -> movieDb.popularPage = page
                MovieType.TOP_RATED -> movieDb.topRatedPage = page
                MovieType.NOW_PLAYING -> movieDb.nowPlayingPage = page
                MovieType.UPCOMING -> movieDb.upcomingPage = page
                MovieType.RECOMMENDED -> recommendedTo?.let {
                    movieDb.recommendations.add(
                        movieBox.get(
                            recommendedTo.toLong()
                        )
                    )
                }
            }

            movieBox.put(movieDb)
        }
    }

    private fun updateMovie(movie: Movie) {
        val movieDb = movieBox.get(movie.id.toLong())
        productionCompanyBox.put(movie.production_companies)
        genreBox.put(movie.genres)
        productionCountryBox.put(movie.production_countries)
        spokenLanguageBox.put(movie.spoken_languages)
        movieDb.apply {
            tagline = movie.tagline
            vote_count = movie.vote_count
            genres.addAll(movie.genres)
            production_companies.addAll(movie.production_companies)
            production_countries.addAll(movie.production_countries)
            spoken_languages.addAll(movie.spoken_languages)
            budget = movie.budget
            homepage = movie.homepage
            imdb_id = movie.homepage
            revenue = movie.revenue
            runtime = movie.runtime
            status = movie.status
            tagline = movie.tagline
            title = movie.original_title
            video = movie.video
            vote_average = movie.vote_average
        }
        movieBox.put(movieDb)
    }

    private fun saveMovieImages(movieImages: MovieImages, id: Int) {
        val movieImagesDb = movieImages.toMovieImagesDb(movieImagesBox, id)
        if (movieImagesBox.query().equal(
                MovieImagesDb_.movieId,
                id.toLong()
            ).build().find().isEmpty()
        ) {
            movieImages.backdrops.map {
                backdropBox.attach(it)
                it.movieImages.target = movieImagesDb
                backdropBox.put(it)
            }
            movieImages.posters.map {
                posterBox.attach(it)
                it.movieImages.target = movieImagesDb
                posterBox.put(it)
            }

            movieImagesDb.backdrops.addAll(movieImages.backdrops)
            movieImagesDb.posters.addAll(movieImages.posters)
            movieImagesBox.put(movieImagesDb)
        }
    }

    private fun saveMovieCredits(movieCredits: MovieCredits, id: Int) {
        val movieCreditsDb = movieCredits.toMovieCreditsDb(movieCreditsBox)
        actorBox.put(movieCredits.actorsList)
        crewBox.put(movieCredits.crew)
        movieCreditsDb.actorsList.addAll(movieCredits.actorsList)
        movieCreditsDb.crew.addAll(movieCredits.crew)
        movieCreditsDb.movie.targetId = id.toLong()
        movieCreditsBox.put(movieCreditsDb)
    }

    private fun saveMovieReviews(movieReviews: PageOfMovieReviews, id: Int) {
        for (movieReview in movieReviews.reviews) {
            if (reviewBox.query().equal(Review_.id, movieReview.id).build().find().isEmpty()) {
                reviewBox.attach(movieReview)
                movieReview.page = movieReviews.page
                movieReview.movieId.targetId = id.toLong()
                reviewBox.put(movieReview)
            }
        }
    }

}