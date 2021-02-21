package com.example.picnat.ui.home


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.PicnatApplication

import com.example.picnat.R
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import com.google.firebase.auth.FirebaseUser
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment() {
    private var adapter: HomeAdapter? = null

    private val viewAuthViewModel by viewModel<AuthViewModel>()

//    @Inject
//    lateinit var viewModelFactory: AuthViewModelFactory
//
//    private val viewModel: AuthViewModel by lazy {
//        ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapter()
        setupView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as PicnatApplication).appComponent.inject(this)
    }

    private fun setupView(){
        viewAuthViewModel.getCurrentUser().observe(this,Observer<FirebaseUser>{
            adapter?.updateData(it)

        })
        recyclerViewHome.adapter = adapter
    }
    private fun initializeAdapter(){
        recyclerViewHome.layoutManager = LinearLayoutManager(activity)
        adapter = HomeAdapter(activity)
        recyclerViewHome.adapter = adapter
    }
}
