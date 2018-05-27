package com.surensth.androidmvvmclean.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Created by surensth on 27 May,2018
 */
@Entity(tableName = "cryptocurrencies")
data class Cryptocurrency(

        @Json(name = "id")
        @PrimaryKey
        val id: String,

        @Json(name = "name")
        val name: String?

) : Serializable