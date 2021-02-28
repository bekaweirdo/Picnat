package com.picnat.core_components.view.edit_text

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.picnat.core_components.R


class EditTextWithTitle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var hint: String
    private var hintColor = ContextCompat.getColor(context, R.color.gray)
    lateinit var text: String
    private var textColor = ContextCompat.getColor(context, R.color.black)
    private var inputType: Int = InputType.TYPE_CLASS_TEXT
    private val editText: EditText

    init {
        inflate(context, R.layout.edit_text_with_title_layout, this)
        getAttributes(attrs)

        editText = this.findViewById(R.id.textInputEditText)
        editText.id = generateViewId()

        if (text.isBlank())
            editText.hint = hint
        else
            editText.setText(text)

        changeInputType()

        editText.setTextColor(textColor)
        editText.setHintTextColor(hintColor)
    }

    private fun getAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithTitle, 0, 0)
        try {
            hint = attributes.getString(R.styleable.EditTextWithTitle_EditTextWithTitle_Hint) ?: ""
            hintColor = attributes.getColor(
                R.styleable.EditTextWithTitle_EditTextWithTitle_HintColor,
                hintColor
            )
            text = attributes.getString(R.styleable.EditTextWithTitle_EditTextWithTitle_Text) ?: ""
            textColor = attributes.getColor(
                R.styleable.EditTextWithTitle_EditTextWithTitle_TextColor,
                textColor
            )
            inputType = attributes.getInt(
                R.styleable.EditTextWithTitle_EditTextWithTitle_InputType,
                inputType
            )
        } finally {
            attributes.recycle()
        }
    }

    private fun changeInputType(){
        when(inputType){
            INPUT_TYPE_TEXT -> editText.inputType = InputType.TYPE_CLASS_TEXT
            INPUT_TYPE_NUMBER -> editText.inputType = InputType.TYPE_CLASS_NUMBER
            INPUT_TYPE_DECIMAL -> editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            INPUT_TYPE_EMAIL -> editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            INPUT_TYPE_PASSWORD -> editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    fun getInput() = editText

    fun getInputText() = editText.text.toString()

    companion object {
        private const val INPUT_TYPE_TEXT = 1
        private const val INPUT_TYPE_NUMBER = 2
        private const val INPUT_TYPE_DECIMAL = 3
        private const val INPUT_TYPE_EMAIL = 4
        private const val INPUT_TYPE_PASSWORD = 5
    }
}