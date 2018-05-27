package com.surensth.androidmvvmclean.view.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.surensth.androidmvvmclean.R
import com.surensth.androidmvvmclean.data.model.Cryptocurrency
import com.surensth.androidmvvmclean.viewmodel.CryptocurrenciesViewModel
import com.surensth.androidmvvmclean.viewmodel.CryptocurrenciesViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var cryptocurrenciesViewModelFactory: CryptocurrenciesViewModelFactory
    lateinit var cryptocurrenciesViewModel: CryptocurrenciesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AndroidInjection.inject(this)

        cryptocurrenciesViewModel = ViewModelProviders.of(this, cryptocurrenciesViewModelFactory).get(
                CryptocurrenciesViewModel::class.java)

        cryptocurrenciesViewModel.loadCryptocurrencies()

        cryptocurrenciesViewModel.cryptocurrenciesResult().observe(this,
                Observer<List<Cryptocurrency>> {
                    cryptotextView.text = "Hello ${it?.size} cryptocurrencies"
                })

        cryptocurrenciesViewModel.cryptocurrenciesError().observe(this, Observer<String> {
            cryptotextView.text = "Hello error $it"
        })
    }

    override fun onDestroy() {
        cryptocurrenciesViewModel.disposeElements()
        super.onDestroy()
    }
}