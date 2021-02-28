package com.picnat.core_components.menu

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picnat.core_components.R
import com.picnat.core_components.extensions.runDelayed
import com.picnat.core_components.menu.adapter.BottomMenuAdapter
import com.picnat.core_components.menu.adapter.MenuAdapterListener
import com.picnat.core_components.view.button.ImageButton

class BottomMenuDialog(ctx: Context, view: View) : MenuAdapterListener, DialogInterface.OnKeyListener,
    Dialog(ctx, R.style.DialogThemeTransparent) {

    private lateinit var menuTitleImage: ImageButton
    private lateinit var menuTitleText: TextView
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var bottomLayoutContainer: ConstraintLayout

    var menuListener: ((position: Int, data: Any?) -> Unit)? = null
    var resultData: Any? = null
    var resultPosition: Int = -1

    override fun onKey(p0: DialogInterface?, p1: Int, p2: KeyEvent?): Boolean {
        TODO("Not yet implemented")
    }

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        setOnKeyListener(this)
        initViews(view)
        setContentView(R.layout.bottom_menu_dialog)
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


    private fun initViews(view: View){
        menuTitleImage = view.findViewById(R.id.menuTitleImage)
        menuTitleText = view.findViewById(R.id.menuTitleMessageLabel)
        menuRecyclerView = view.findViewById(R.id.menuRecyclerView)
        bottomLayoutContainer = view.findViewById(R.id.bottomLayoutContainer)
    }

    fun listener(menuListener: ((position: Int, data: Any?) -> Unit)){
        runDelayed(500) {
            this.menuListener = menuListener
        }
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
    }

}