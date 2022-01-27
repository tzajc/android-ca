package com.example.android_ca.core.di

import com.example.android_ca.BuildConfig
import com.example.android_ca.feature.data.RickAndMortyApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Interceptor> {
        HttpLoggingInterceptor()
            .apply {
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }
    }

    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single {
        Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com.api/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(RickAndMortyApi::class.java)
    }
}