package com.picnat.core_components.view.edit_text

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import com.picnat.core_components.R


class EditTextWithTitle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var hint: String
    lateinit var text: String
    private val editText: EditText

    init {
        inflate(context, R.layout.edit_text_with_title_layout, this)
        getAttributes(attrs)

        editText = this.findViewById(R.id.textInputEditText)
        if (text.isBlank())
            editText.hint = hint
        else
            editText.setText(text)
    }

    private fun getAttributes(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithTitle, 0, 0)
        try {
            hint = ta.getString(R.styleable.EditTextWithTitle_EditTextWithTitle_Hint) ?: ""
            text = ta.getString(R.styleable.EditTextWithTitle_EditTextWithTitle_Text) ?: ""
        } finally {
            ta.recycle()
        }
    }

    fun getInput() = editText.text.toString()
}