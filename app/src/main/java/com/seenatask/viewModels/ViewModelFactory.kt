package com.seenatask.viewModels

import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seenatask.app.Constant
import com.seenatask.ui.activity.MainActivity
import com.seenatask.ui.adaptor.MoviesAdapter
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private var movieViewModel: MovieViewModel,

    ) {


    fun getMovieModelData(
        mainActivity: MainActivity,
        recyclerView: RecyclerView,
        progress: ContentLoadingProgressBar,
        linearProgress: LinearLayoutCompat,
    ) {
        movieViewModel = ViewModelProvider(mainActivity)[MovieViewModel::class.java]
        movieViewModel.getData(progress, linearProgress,
            Constant.QUERY_TITLE,
            Constant.APP_ID)

        movieViewModel.getLiveData()?.observe(mainActivity) {
            recyclerView.layoutManager = LinearLayoutManager(mainActivity)
            recyclerView.adapter = MoviesAdapter(it, mainActivity.supportFragmentManager)
        }

    }


}