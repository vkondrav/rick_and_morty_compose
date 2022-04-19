package com.vkondrav.playground.app.core

import com.vkondrav.playground.app.page1.di.page1Module
import com.vkondrav.playground.app.page2.di.page2Module
import com.vkondrav.playground.app.page3.di.page3Module
import org.koin.core.KoinApplication

fun KoinApplication.appModules() = modules(
    page1Module,
    page2Module,
    page3Module,
)