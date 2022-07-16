package com.seenatask.listener

import com.seenatask.viewModels.ViewModelFactory
import com.seenatask.ui.activity.ViewModelModule
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {


    fun getViewModel(): ViewModelFactory
}