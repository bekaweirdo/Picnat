package com.picnat.core.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleEventLiveData<T> : MutableLiveData<T>() {

    var called = false

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, { t ->
            if(called){
                observer.onChanged(t)
                called = false
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        called = true
        super.setValue(t)
    }

    override fun postValue(t: T) {
        called = true
        super.postValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }
}

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }
    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    @MainThread
    override fun postValue(t: T) {
        pending.set(true)
        super.postValue(t)
    }
    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}