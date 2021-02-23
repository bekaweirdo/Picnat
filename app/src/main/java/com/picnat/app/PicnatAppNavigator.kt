package com.picnat.app

import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PicnatAppNavigator(activity: MainActivity, containerRes: Int) :
    AppNavigator(
        activity,
        containerRes
    )