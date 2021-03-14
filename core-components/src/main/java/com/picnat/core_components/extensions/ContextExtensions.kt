package com.picnat.core_components.extensions

import android.os.Handler
import android.os.Looper
import java.util.concurrent.TimeUnit

fun runDelayed(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, action: () -> Unit){
    Handler(Looper.getMainLooper()).postDelayed(action,timeUnit.toMillis(delay))
}