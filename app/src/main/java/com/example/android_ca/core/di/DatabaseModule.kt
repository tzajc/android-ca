package com.example.android_ca.core.di

import androidx.room.Room.databaseBuilder
import com.example.android_ca.core.app.App
import com.example.android_ca.feature.data.local.RickAndMortyDatabase
import com.example.android_ca.feature.data.local.dao.ExampleDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDataBase(application: App): RickAndMortyDatabase {
        return databaseBuilder(application, RickAndMortyDatabase::class.java, "RickAndMorty")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: RickAndMortyDatabase): ExampleDAO {
        return dataBase.exampleDAO
    }

    single {
        provideDataBase(androidApplication() as App)
    }

    single {
        provideDao(get())
    }
}