package com.picnat.core.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.picnat.core.App
import com.picnat.core.navigation.api.GlobalNavigator
import com.picnat.core.navigation.api.LocalNavigator
import com.picnat.core.utils.getChanges
import org.koin.android.ext.android.inject

abstract class BaseFragment<VM : BaseFeatureVM?> : Fragment() {

    companion object {
        const val LOADING = "LOADING"
    }

    protected val localNavigator: LocalNavigator by inject()

    protected val globalNavigator: GlobalNavigator by inject()

    protected abstract val viewModel: VM?

    abstract val getLayout: Int

    abstract fun initViews(view: View, savedInstanceState: Bundle?)

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
        initViews(view, savedInstanceState)
        return view
    }


    open fun onBindViewModel(viewModel: VM) {
        viewModel?.errorMessage?.observe {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel?._showLoading?.observe { showLoading ->
            val intent = Intent(LOADING).apply {
                putExtra(LOADING, showLoading)
            }
            LocalBroadcastManager.getInstance(App.appContext).sendBroadcast(intent)
        }
    }

    protected fun <T> LiveData<T>.observe(onChanged: (T) -> Unit) {
        observe(viewLifecycleOwner, onChanged)
    }

    fun MutableLiveData<String>.bind(editText: EditText) {
        editText.getChanges {
            if(this.value != it)
                this.value = it
        }
    }

    fun MutableLiveData<String>.doubleBind(editText: EditText) {
        editText.getChanges {
            if(this.value != it)
                this.value = it
        }

        this.observe{
            if(editText.text.toString() != it)
                editText.setText(it)
        }
    }
}