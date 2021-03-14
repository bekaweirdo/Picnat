package com.picnat.core_components.menu

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picnat.core_components.R
import com.picnat.core_components.extensions.runDelayed
import com.picnat.core_components.menu.adapter.BottomMenuAdapter
import com.picnat.core_components.menu.adapter.MenuAdapterListener
import com.picnat.core_components.menu.slidemenu.SlidingUpPanelLayout
import com.picnat.core_components.view.button.ImageButton

@Suppress("DEPRECATION")
class BottomMenuDialog(ctx: Context) : MenuAdapterListener, DialogInterface.OnKeyListener,
    Dialog(ctx, R.style.DialogThemeTransparent) {

    private lateinit var menuTitleImage: ImageButton
    private lateinit var menuTitleText: TextView
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var bottomLayoutContainer: SlidingUpPanelLayout
    private lateinit var dragContainerView: ConstraintLayout

    var startState = SlidingUpPanelLayout.PanelState.ANCHORED
    var isScrollableWithTouchDisable: Boolean = false

    var menuListener: ((position: Int, data: Any?) -> Unit)? = null
    var resultData: Any? = null
    var resultPosition: Int = -1

    var slideListener: ((offset: Float) -> Unit)? = null


    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bottomLayoutContainer.panelState === SlidingUpPanelLayout.PanelState.COLLAPSED || bottomLayoutContainer.panelState === SlidingUpPanelLayout.PanelState.HIDDEN) {
                dismiss()
            } else {
                bottomLayoutContainer.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
            return true
        }
        return false
    }

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        setOnKeyListener(this)
        setContentView(R.layout.bottom_menu_dialog)
        initViews()
    }


    fun menuTopViewEnable(isEnable: Boolean){
        if(isEnable) {
            menuTitleImage.visibility = View.VISIBLE
            menuTitleText.visibility = View.VISIBLE
        }
    }

    fun setMenuMessage(message: String?){
        menuTitleText.visibility = View.VISIBLE
        menuTitleText.text = message
    }

    fun setAdapter(adapter: BottomMenuAdapter<*>){
        adapter.adapterListener = this
        menuRecyclerView.visibility = View.VISIBLE
        menuRecyclerView.layoutManager = LinearLayoutManager(context)
        menuRecyclerView.adapter = adapter
    }

    fun scrollableViewDisable(isEnabled: Boolean) {
        isScrollableWithTouchDisable = !isEnabled
        bottomLayoutContainer.isTouchEnabled = isEnabled
    }

    fun addSlideView(view: View) {
        dragContainerView?.addView(view)
    }

    fun changeRootContainerLayoutParams(layoutParams: SlidingUpPanelLayout.LayoutParams) {
        dragContainerView.layoutParams = layoutParams
    }

    private fun initViews(){
        menuTitleImage = findViewById(R.id.menuTitleImage)
        menuTitleText = findViewById(R.id.menuTitleMessageLabel)
        menuRecyclerView = findViewById(R.id.menuRecyclerView)
        bottomLayoutContainer = findViewById(R.id.bottomLayoutContainer)
        dragContainerView = findViewById(R.id.dragContainerView)
    }

    fun listener(menuListener: ((position: Int, data: Any?) -> Unit)){
        runDelayed(500) {
            this.menuListener = menuListener
        }
    }

    fun setBottomFragment(fm: FragmentManager,fragment: Fragment){
//        if(!initFragmentState(fm)) return
            startState = SlidingUpPanelLayout.PanelState.EXPANDED

    }

    private fun initMenuListener() {
        bottomLayoutContainer!!.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                slideListener?.invoke(slideOffset)
            }

            override fun onPanelStateChanged(
                panel: View,
                previousState: SlidingUpPanelLayout.PanelState,
                newState: SlidingUpPanelLayout.PanelState
            ) {

                if (previousState === SlidingUpPanelLayout.PanelState.DRAGGING && newState === SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    if (resultPosition != -1)
                        menuListener?.let { it(resultPosition, resultData) }
                    dismiss()
                }
            }
        })
    }

//    private fun initFragmentState(fm: FragmentManager): Boolean {
//        mFragmentManager = fm
//        menuBottomFragmentContainer.show()
//        if (helperFragment == null) {
//            val frag = menuBottomFragmentContainer.inflate(R.layout.sp_bottom_menu_dialog_fragment_hepler)
//            menuBottomFragmentContainer.addView(frag)
//            helperFragment =
//                mFragmentManager?.fragments?.find { it is SPBottomMenuHelperFragment } as? SPBottomMenuHelperFragment
//            childFragmentManager = helperFragment?.childFragmentManager
//        }
//        if (helperFragment == null) return false
//        return true
//    }

    private fun showSlidePanel() {
        Handler().postDelayed({
        bottomLayoutContainer.setIsDisableAllTouch(isScrollableWithTouchDisable)
        bottomLayoutContainer.anchorPoint = 0.0f
        bottomLayoutContainer.panelHeight = 0
        bottomLayoutContainer.panelState = startState
        }, 70)
    }

    override fun show(){
        super.show()
        resultData = null
        resultPosition = -1
    }

    override fun onItemClickListener(position: Int, data: Any?) {
        resultData = data
        resultPosition = position
        hideBottomMenuDialog()
    }

    private fun hideBottomMenuDialog(){
        bottomLayoutContainer.visibility = View.GONE
        bottomLayoutContainer.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
    }

}