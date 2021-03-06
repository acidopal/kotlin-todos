package com.tutorial.learnlinuxpro.presentation.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.kodeartisan.kotlintemplate.BaseApp
import com.kodeartisan.kotlintemplate.data.source.local.sqliteAsset.AssetSQLiteOpenHelperFactory
import com.tutorial.learnlinuxpro.data.source.local.room.AppDatabase
import com.tutorial.learnlinuxpro.data.source.local.room.Migration3to4
import com.tutorial.learnlinuxpro.data.utils.schedulers.BaseSchedulerProvider
import com.tutorial.learnlinuxpro.data.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class ApplicationModule(private val app: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): BaseApp = app

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "data.db")
                .openHelperFactory(AssetSQLiteOpenHelperFactory())
                .addMigrations(Migration3to4())
                .allowMainThreadQueries()
                .build()
    }


}