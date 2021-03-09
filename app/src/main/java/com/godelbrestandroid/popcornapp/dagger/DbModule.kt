package com.godelbrestandroid.popcornapp.dagger

import android.content.Context
import com.godelbrestandroid.popcornapp.data.db.entities.MyObjectBox
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDb(context: Context): BoxStore {
        return MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}