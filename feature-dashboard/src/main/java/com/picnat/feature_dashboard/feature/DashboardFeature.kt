package com.picnat.feature_dashboard.feature

import com.picnat.feature_dashboard.presentation.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DashboardFeature {

    val dashboardModule = module {
        viewModel { DashboardViewModel() }
    }

}