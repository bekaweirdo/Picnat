package com.picnat.core_components.view.button

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.picnat.core_components.R


class RoundedButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var buttonColor = 0
    private var buttonStrokeColor = 0
    private val roundedButtonLayout: FrameLayout

    private lateinit var buttonText: String
    private var buttonTextSize = R.dimen.dimen_p_14
    private var buttonTextColor = ContextCompat.getColor(context, R.color.black)
    private val roundedButtonText: TextView

    init {
        inflate(context, R.layout.rounded_button_layout, this)

        getAttributes(attrs)
        roundedButtonLayout = this.findViewById(R.id.roundedButtonLayout)
        roundedButtonText = this.findViewById(R.id.roundedButtonText)
        val roundedButtonLayoutDrawable = roundedButtonLayout.background as GradientDrawable

        roundedButtonText.text = buttonText
        roundedButtonText.setTextSize(
            TypedValue.COMPLEX_UNIT_PX, resources.getDimension(
                buttonTextSize
            )
        )
        roundedButtonText.setTextColor(buttonTextColor)

        roundedButtonLayoutDrawable.setStroke(
            resources.getDimensionPixelSize(R.dimen.dimen_p_1), buttonStrokeColor
        )

        roundedButtonLayoutDrawable.setColor(buttonColor)
    }

    private fun getAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundedButton, 0, 0)
        try {
            buttonColor = attributes.getColor(
                    R.styleable.RoundedButton_RoundedButton_Color,
                    ContextCompat.getColor(context, R.color.black)
                )
            buttonStrokeColor = attributes.getColor(
                R.styleable.RoundedButton_RoundedButton_StrokeColor,
                ContextCompat.getColor(context, R.color.black)
            )

            buttonText = attributes.getString(R.styleable.RoundedButton_RoundedButton_Text) ?: ""
            buttonTextColor = attributes.getColor(
                R.styleable.RoundedButton_RoundedButton_TextColor,
                buttonTextColor
            )
            buttonTextSize = attributes.getInt(
                R.styleable.RoundedButton_RoundedButton_TextSize,
                buttonTextSize
            )
        } finally {
            attributes.recycle()
        }
    }
}