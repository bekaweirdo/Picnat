import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.MutableLiveData

//package com.picnat.core.utils
//
//import android.util.Log
//import androidx.annotation.MainThread
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import java.util.concurrent.atomic.AtomicBoolean
//
//class SingleLiveEvent<T> : MutableLiveData<T>() {
//
//    private val pending = AtomicBoolean(false)
//
//    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
//
//        if (hasActiveObservers()) {
//            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
//        }
//
//        // Observe the internal MutableLiveData
//        super.observe(owner, Observer<T> { t ->
//            if (pending.compareAndSet(true, false)) {
//                observer.onChanged(t)
//            }
//        })
//    }
//
//    @MainThread
//    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
//
//    }
//
//    @MainThread
//    override fun setValue(t: T?) {
//        pending.set(true)
//        super.setValue(t)
//    }
//
//    /**
//     * Used for cases where T is Void, to make calls cleaner.
//     */
//    @MainThread
//    fun call() {
//        value = null
//    }
//
//    companion object {
//        private const val TAG = "SingleLiveEvent"
//    }
//}

fun MutableLiveData<String>.bind(editText: EditText) {
    editText.addTextChangedListener(object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if(this@bind.value != s.toString())
                this@bind.value = s.toString()
        }

        override fun afterTextChanged(s: Editable?) {}

    })
}