package com.picnat.core_components.menu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picnat.core_components.R

class BottomMenuHelperFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_menu_view,container,false)
    }

    interface BottomMenuListener {
        fun showFragment(fragment: Fragment)
        fun popBackStack()
    }

    interface BottomMenuNavFragment {
        fun setBottomMenuNavInterface(listener: BottomMenuListener)
    }
}