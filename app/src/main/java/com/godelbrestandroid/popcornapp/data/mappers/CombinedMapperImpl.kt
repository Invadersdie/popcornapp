package com.godelbrestandroid.popcornapp.data.mappers

import com.godelbrestandroid.popcornapp.data.models.CommonScreenItem
import com.godelbrestandroid.popcornapp.data.models.HomeCombined
import com.godelbrestandroid.popcornapp.data.models.MoviesCombined
import com.godelbrestandroid.popcornapp.data.models.TvShowsCombined
import com.godelbrestandroid.popcornapp.utils.Types
import javax.inject.Inject

class CombinedMapperImpl @Inject constructor(private val shortInfoMapper: ShortInfoMapper) :
    CombinedMapper {
    override fun tvShowsCombinedToCommonList(tvShowsCombined: TvShowsCombined): List<CommonScreenItem> {
        return listOf(
            CommonScreenItem(
                Types.TVSHOW_POPULAR,
                shortInfoMapper.pageOfTvShowsToShortInfoList(tvShowsCombined.popularTvShows)
            ),
            CommonScreenItem(
                Types.TVSHOW_TOP_RATED,
                shortInfoMapper.pageOfTvShowsToShortInfoList(tvShowsCombined.topRatedTvShows)
            ),
            CommonScreenItem(
                Types.TVSHOW_ON_TV,
                shortInfoMapper.pageOfTvShowsToShortInfoList(tvShowsCombined.onTheAirTvShows)
            ),
            CommonScreenItem(
                Types.TVSHOW_AIRING_TODAY,
                shortInfoMapper.pageOfTvShowsToShortInfoList(tvShowsCombined.airingTvShows)
            )
        )
    }

    override fun moviesCombinedToCommonList(moviesCombined: MoviesCombined): List<CommonScreenItem> {
        return listOf(
            CommonScreenItem(
                Types.MOVIE_POPULAR,
                shortInfoMapper.pageOfMoviesToShortInfoList(moviesCombined.popularMovies)
            ), CommonScreenItem(
                Types.MOVIE_TOP_RATED,
                shortInfoMapper.pageOfMoviesToShortInfoList(moviesCombined.topRatedMovies)
            ),
            CommonScreenItem(
                Types.MOVIE_UPCOMING,
                shortInfoMapper.pageOfMoviesToShortInfoList(moviesCombined.upcomingMovies)
            ),


            CommonScreenItem(
                Types.MOVIE_NOW_PLAYING,
                shortInfoMapper.pageOfMoviesToShortInfoList(moviesCombined.nowPlayingMovies)
            )
        )
    }

    override fun homeCombinedToCommonList(homeCombined: HomeCombined): List<CommonScreenItem> {
        return listOf(
            CommonScreenItem(
                Types.MOVIE,
                shortInfoMapper.pageOfMoviesToShortInfoList(homeCombined.movies)
            ),
            CommonScreenItem(
                Types.TVSHOW,
                shortInfoMapper.pageOfTvShowsToShortInfoList(homeCombined.tvShows)
            ),
            CommonScreenItem(
                Types.PEOPLE,
                shortInfoMapper.pageOfPeopleToShortInfoList(homeCombined.people)
            )
        )
    }
}