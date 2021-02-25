package com.picnat.core.data.resource_provider

import android.content.Context

class ResourceProvider(private val context : Context) {

    fun getString(stringRes : Int) = context.getString(stringRes)

}