package com.picnat.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VM : ViewModel?> : Fragment() {

    protected abstract val viewModel : VM?

    abstract val getLayout: Int

    abstract fun initViews(view : View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel != null)
            onBindViewModel(viewModel!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayout, container, false)
        initViews(view)
        return view
    }


    open fun onBindViewModel(viewModel: VM) {}

    protected fun <T> LiveData<T>.observe(onChanged: (T) -> Unit) {
        observe(viewLifecycleOwner, onChanged)
    }
}