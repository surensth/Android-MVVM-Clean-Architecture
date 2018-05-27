package com.surensth.androidmvvmclean.data.source.local
import android.arch.persistence.room.Database

import android.arch.persistence.room.RoomDatabase
import com.surensth.androidmvvmclean.data.model.Cryptocurrency

@Database(entities = arrayOf(Cryptocurrency::class), version = 1)
abstract class Database : RoomDatabase() {
  abstract fun cryptocurrenciesDao(): CryptocurrenciesDao
}