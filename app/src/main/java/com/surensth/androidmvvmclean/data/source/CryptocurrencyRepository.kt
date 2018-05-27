package com.surensth.androidmvvmclean.data.source

import android.util.Log
import com.surensth.androidmvvmclean.data.model.Cryptocurrency
import com.surensth.androidmvvmclean.data.source.local.CryptocurrenciesDao
import com.surensth.androidmvvmclean.data.source.remote.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class CryptocurrencyRepository @Inject constructor(val apiInterface: ApiInterface,
                                                   val cryptocurrenciesDao: CryptocurrenciesDao) {

    fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
        val observableFromApi = getCryptocurrenciesFromApi()
        val observableFromDb = getCryptocurrenciesFromDb()
        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    fun getCryptocurrenciesFromApi(): Observable<List<Cryptocurrency>> {
        return apiInterface.getCryptocurrencies("0")
                .doOnNext {
                    Log.v("REPOSITORY API * ", it.size.toString())
                    for (item in it) {
                        cryptocurrenciesDao.insertCryptocurrency(item)
                    }
                }
    }

    fun getCryptocurrenciesFromDb(): Observable<List<Cryptocurrency>> {
        return cryptocurrenciesDao.queryCryptocurrencies()
                .toObservable()
                .doOnNext {
                    //Print log it.size :)
                    Log.v("REPOSITORY DB *** ", it.size.toString())
                }
    }
}