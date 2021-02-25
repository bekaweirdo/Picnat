package com.picnat.core_components.view.edit_text

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import com.picnat.core_components.R


class EditTextWithTitle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var hint: String
    lateinit var text: String
    private var inputType : Int = InputType.TYPE_CLASS_TEXT
    private val editText: EditText

    init {
        inflate(context, R.layout.edit_text_with_title_layout, this)
        getAttributes(attrs)

        editText = this.findViewById(R.id.textInputEditText)
        if (text.isBlank())
            editText.hint = hint
        else
            editText.setText(text)
        editText.inputType = inputType
    }

    private fun getAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithTitle, 0, 0)
        try {
            hint = attributes.getString(R.styleable.EditTextWithTitle_EditTextWithTitle_Hint) ?: ""
            text = attributes.getString(R.styleable.EditTextWithTitle_EditTextWithTitle_Text) ?: ""
            inputType = attributes.getInt(R.styleable.EditTextWithTitle_EditTextWithTitle_InputType, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        } finally {
            attributes.recycle()
        }
    }

    fun getInput() = editText.text.toString()
}