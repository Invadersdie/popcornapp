package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.db.entities.movie.TvShowDb
import com.godelbrestandroid.popcornapp.data.db.entities.movie.TvShowDb_
import com.godelbrestandroid.popcornapp.data.db.entities.movie.toTvShow
import com.godelbrestandroid.popcornapp.data.db.entities.movie.toTvShowDb
import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.ProductionCompany
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.CreatedBy
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.Network
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.Season
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow
import com.godelbrestandroid.popcornapp.data.internet.server_api.TvShowsApi
import com.godelbrestandroid.popcornapp.utils.NetworkUtils
import io.objectbox.BoxStore
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(db: BoxStore, networkUtils: NetworkUtils) :
    TvShowsRepository, BaseRepository(networkUtils) {

    private val tvShowBox = db.boxFor(TvShowDb::class.java)
    private val createdByBox = db.boxFor(CreatedBy::class.java)
    private val genreBox = db.boxFor(Genre::class.java)
    private val networkBox = db.boxFor(Network::class.java)
    private val productionCompanyBox = db.boxFor(ProductionCompany::class.java)
    private val seasonBox = db.boxFor(Season::class.java)


    @Inject
    lateinit var tvShowsApi: TvShowsApi

    override fun downloadPopularTvShows(page: Int): Observable<List<TvShow>> {
        val query = tvShowBox.query().equal(TvShowDb_.popularPage, page.toLong()).build()
        val dbRequest = RxQuery.observable(query).map { i -> i.map { it.toTvShow() } }
        val netRequest = tvShowsApi.getPopularTvShows(page)
            .doOnSuccess { saveTvShows(it.tvShowsList, page, TvShowType.POPULAR) }
        return combine(dbRequest, netRequest)
    }

    override fun downloadTvShowDetails(id: Int): Observable<TvShow> {
        val query = tvShowBox.query().equal(TvShowDb_.id, id.toLong()).build()
        val dbRequest = RxQuery.observable(query).flatMapIterable { it.map { it.toTvShow() } }
        val netRequest = tvShowsApi.getTvShowsDetails(id).doOnSuccess { updateTvShow(it) }
        return combine(dbRequest, netRequest)
    }

    override fun downloadAiringTvShows(page: Int): Observable<List<TvShow>> {
        val query = tvShowBox.query().equal(TvShowDb_.airingPage, page.toLong()).build()
        val dbRequest = RxQuery.observable(query).map { it.map { it.toTvShow() } }
        val netRequest = tvShowsApi.getAiringTvShows(page)
            .doOnSuccess { saveTvShows(it.tvShowsList, page, TvShowType.AIRING) }
        return combine(dbRequest, netRequest)
    }

    override fun downloadOnTheAirTvShows(page: Int): Observable<List<TvShow>> {
        val query = tvShowBox.query().equal(TvShowDb_.onTheAirPage, page.toLong()).build()
        val dbRequest = RxQuery.observable(query).map { it.map { it.toTvShow() } }
        val netRequest =
            tvShowsApi.getOnTheAirTvShows(page)
                .doOnSuccess { saveTvShows(it.tvShowsList, page, TvShowType.ON_THE_AIR) }
        return combine(dbRequest, netRequest)
    }

    override fun downloadTopRatedTvShows(page: Int): Observable<List<TvShow>> {
        val query = tvShowBox.query().equal(TvShowDb_.topRatedPage, page.toLong()).build()
        val dbRequest = RxQuery.observable(query).map { it.map { it.toTvShow() } }
        val netRequest =
            tvShowsApi.getTopRatedTvShows(page)
                .doOnSuccess { saveTvShows(it.tvShowsList, page, TvShowType.TOP_RATED) }
        return combine(dbRequest, netRequest)
    }

    enum class TvShowType {
        AIRING,
        ON_THE_AIR,
        TOP_RATED,
        POPULAR
    }

    private fun saveTvShows(
        list: List<TvShow>,
        page: Int = 0,
        type: TvShowType,
        recommendedTo: Int? = null
    ) {
        for (tvShow in list) {
            val tvShowDb = tvShow.toTvShowDb(tvShowBox)

            when (type) {
                TvShowType.AIRING -> tvShowDb.airingPage = page
                TvShowType.ON_THE_AIR -> tvShowDb.onTheAirPage = page
                TvShowType.TOP_RATED -> tvShowDb.topRatedPage = page
                TvShowType.POPULAR -> tvShowDb.popularPage = page
            }
            recommendedTo?.let { tvShowDb.recommendations.add(tvShowBox.get(recommendedTo.toLong())) }

            tvShowBox.put(tvShowDb)
        }
    }

    private fun updateTvShow(tvShow: TvShow) {
        val tvShowDb = tvShowBox.get(tvShow.id.toLong())
        createdByBox.put(tvShow.created_by)
        genreBox.put(tvShow.genres)
        networkBox.put(tvShow.networks)
        productionCompanyBox.put(tvShow.production_companies)
        seasonBox.put(tvShow.seasons)

        tvShowDb.apply {
            homepage = tvShow.homepage
            in_production = tvShow.in_production
            languages = tvShow.languages
            last_air_date = tvShow.last_air_date
            number_of_episodes = tvShow.number_of_episodes
            number_of_seasons = tvShow.number_of_seasons
            status = tvShow.status
            type = tvShow.type
            last_episode_to_air = tvShow.last_episode_to_air

            created_by.addAll(tvShow.created_by)
            genres.addAll(tvShow.genres)
            networks.addAll(tvShow.networks)
            production_companies.addAll(tvShow.production_companies)
            seasons.addAll(tvShow.seasons)
        }
        tvShowBox.put(tvShowDb)
    }
}