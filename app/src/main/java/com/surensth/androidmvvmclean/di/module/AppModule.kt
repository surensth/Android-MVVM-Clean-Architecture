package com.surensth.androidmvvmclean.di.module


import android.arch.persistence.room.Room
import com.surensth.androidmvvmclean.MyApplication
import com.surensth.androidmvvmclean.data.source.local.CryptocurrenciesDao
import com.surensth.androidmvvmclean.data.source.local.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): MyApplication = app

    @Provides
    @Singleton
    fun provideCryptoCurrenciesDatabase(app: MyApplication): Database = Room.databaseBuilder(app, Database::class.java, "cryptocurrencies_db").build()

    @Provides
    @Singleton
    fun provideCryptocurrenciesDao(database: Database): CryptocurrenciesDao = database.cryptocurrenciesDao()
}