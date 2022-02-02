package com.example.android_ca.core.di

import org.koin.core.module.Module

val injector: List<Module> = listOf(
    networkModule,
    appModule,
    databaseModule
)

