package com.picnat.core_components.view.button

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.picnat.core_components.extensions.runDelayed

class ImageButton : ConstraintLayout, View.OnTouchListener{

    constructor(context: Context): super(context) {
        init(null,0)
    }

    constructor(context: Context, attrs: AttributeSet): super(context,attrs){
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
        init(attrs,defStyle)
    }

    private fun init(attrs: AttributeSet?,defStyle: Int){
        setOnTouchListener(this)
    }
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                playEffectAnimation(view!!, false)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                runDelayed(50, action = {
                    this.isEnabled = false
                    runDelayed(300, action = {
                        this.isEnabled = true
                    })
                })
                playEffectAnimation(view!!, true)
            }
        }
        return false
    }

    private fun playEffectAnimation(view: View, isFadeIn: Boolean) {
        val animationTime: Long = 200
        var animation: ObjectAnimator? = null

        animation = if (!isFadeIn) {
            ObjectAnimator.ofFloat(view, "alpha", 1f, .2f)
        } else {
            ObjectAnimator.ofFloat(view, "alpha", .7f, 1f)
        }
        animation.duration = animationTime
        animation?.start()

    }
}