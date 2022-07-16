package com.seenatask.ui.activity

import android.annotation.SuppressLint
import android.view.View
import com.seenatask.base.BaseActivity
import com.seenatask.databinding.ActivityMainBinding
import com.seenatask.listener.DaggerViewModelComponent
import com.seenatask.listener.ViewModelComponent

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    // lateinit var viewModel: MovieViewModel

    /**
     *for support onCreate method for all subClasses
     */
    @SuppressLint("SetTextI18n")
    override fun initializeComponents() {

        title?.text = "Seena Task"

        val component: ViewModelComponent = DaggerViewModelComponent.create()
        component.getViewModel().getMovieModelData(this,
            recyclerView!!,
            binding.progress.progressCircular,
            binding.progress.linearProgress)
    }

    /**
     * used with ViewBinding to return binding.getRoot() from subClasses
     *
     * @return the ViewBinding root
     */
    override fun getLayoutRoot(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * @return true if you need support toolbar in subClass or return false if not
     */
    override fun isToolBarEnabled(): Boolean = true

    /**
     * @return true if you need support back in toolbar in subClass or return false if not
     */
    override fun isEnableBack(): Boolean = false

    /**
     * @return true if you need support bottomBar in subClass or return false if not
     */
    override fun isBottomNavBarEnabled(): Boolean = false

    /**
     * @return true if you need support recyclerView in subClass or return false if not
     */
    override fun isRecyclerViewEnabled(): Boolean = true
}