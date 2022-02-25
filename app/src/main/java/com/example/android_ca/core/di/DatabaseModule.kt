package com.example.android_ca.core.di

import androidx.room.Room.databaseBuilder
import com.example.android_ca.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "RickAndMorty"
        )
    }

    single {
        get<AppDatabase>().episodeDao;
    }

    single {
        get<AppDatabase>().locationDao;
    }

    single {
        get<AppDatabase>().characterDao;
    }
}