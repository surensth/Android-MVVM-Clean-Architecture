package com.surensth.androidmvvmclean.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.surensth.androidmvvmclean.data.model.Cryptocurrency
import com.surensth.androidmvvmclean.data.source.CryptocurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class CryptocurrenciesViewModel @Inject constructor(
        private val cryptocurrencyRepository: CryptocurrencyRepository) : ViewModel() {

    var cryptocurrenciesResult: MutableLiveData<List<Cryptocurrency>> = MutableLiveData()
    var cryptocurrenciesError: MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<Cryptocurrency>>

    fun cryptocurrenciesResult(): LiveData<List<Cryptocurrency>> {
        return cryptocurrenciesResult
    }

    fun cryptocurrenciesError(): LiveData<String> {
        return cryptocurrenciesError
    }

    fun loadCryptocurrencies() {

        disposableObserver = object : DisposableObserver<List<Cryptocurrency>>() {
            override fun onComplete() {

            }

            override fun onNext(cryptocurrencies: List<Cryptocurrency>) {
                cryptocurrenciesResult.postValue(cryptocurrencies)
            }

            override fun onError(e: Throwable) {
                cryptocurrenciesError.postValue(e.message)
            }
        }

        cryptocurrencyRepository.getCryptocurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if (null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }

}