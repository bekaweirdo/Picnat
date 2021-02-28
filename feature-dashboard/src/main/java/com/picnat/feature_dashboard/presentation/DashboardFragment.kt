package com.picnat.feature_dashboard.presentation

import android.view.View
import com.picnat.core.base.BaseFragment
import com.picnat.feature_dashboard.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<DashboardViewModel>() {

    override val getLayout: Int
        get() = R.layout.dashboard_fragment
    override val viewModel: DashboardViewModel by viewModel()

    override fun initViews(view: View) {

    }
}