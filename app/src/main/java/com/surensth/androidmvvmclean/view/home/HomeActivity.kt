package com.surensth.androidmvvmclean.view.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.surensth.androidmvvmclean.R
import com.surensth.androidmvvmclean.data.model.Cryptocurrency
import com.surensth.androidmvvmclean.data.source.remote.ApiInterface
import com.surensth.androidmvvmclean.network.ApiClient
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AndroidInjection.inject(this)

        showCryptoCurriencies()
    }

    private fun showCryptoCurriencies() {
        val cryptocurrenciesResponse = getCryptocurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

        val disposableObserver =
                cryptocurrenciesResponse.subscribeWith(object : DisposableObserver<List<Cryptocurrency>>() {
                    override fun onComplete() {
                    }

                    override fun onNext(cryptocurrencies: List<Cryptocurrency>) {
                        val listSize = cryptocurrencies.size
                        Log.v("ITEMS **** ", listSize.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.v("ERROR *** ", e.message)
                    }

                })

        compositeDisposable.addAll(disposableObserver)

    }

    private fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
        val retrofit = ApiClient.getClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface.getCryptocurrencies("0")
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}