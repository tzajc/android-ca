package com.example.android_ca.core.di

import org.koin.core.module.Module

val injector: List<Module> = featureModule
    .plus(appModule)
    .plus(networkModule)
    .plus(databaseModule)


