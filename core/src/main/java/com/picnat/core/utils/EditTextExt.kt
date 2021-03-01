package com.picnat.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.getChanges(callback : (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            callback.invoke(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {}

    })
}