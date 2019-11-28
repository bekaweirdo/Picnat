package com.example.picnat.repository

interface AuthInterface {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}