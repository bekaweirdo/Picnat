package com.example.picnat

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.androidx.AppNavigator

class PicnatAppNavigator(activity: FragmentActivity) :
    AppNavigator(
        activity,
        R.id.container
    )