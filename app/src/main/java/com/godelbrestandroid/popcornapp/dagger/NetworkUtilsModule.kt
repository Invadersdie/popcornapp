package com.godelbrestandroid.popcornapp.dagger

import android.content.Context
import com.godelbrestandroid.popcornapp.utils.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class NetworkUtilsModule {

    @Provides
    fun getNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context = context)
    }
}