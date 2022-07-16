package com.seenatask.ui.activity

import com.seenatask.viewModels.MovieViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesMovieModel(): MovieViewModel {
        return MovieViewModel()
    }


}