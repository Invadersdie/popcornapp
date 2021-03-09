package com.godelbrestandroid.popcornapp.data.mappers

import com.godelbrestandroid.popcornapp.data.models.CommonScreenItem
import com.godelbrestandroid.popcornapp.data.models.HomeCombined
import com.godelbrestandroid.popcornapp.data.models.MoviesCombined
import com.godelbrestandroid.popcornapp.data.models.TvShowsCombined


interface CombinedMapper {
    fun moviesCombinedToCommonList(moviesCombined: MoviesCombined): List<CommonScreenItem>
    fun homeCombinedToCommonList(homeCombined: HomeCombined): List<CommonScreenItem>
    fun tvShowsCombinedToCommonList(tvShowsCombined: TvShowsCombined): List<CommonScreenItem>
}