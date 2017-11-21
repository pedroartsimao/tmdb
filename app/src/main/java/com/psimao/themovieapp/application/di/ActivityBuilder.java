package com.psimao.themovieapp.application.di;

import com.psimao.themovieapp.presentation.moviedetails.MovieDetailsActivity;
import com.psimao.themovieapp.presentation.moviedetails.MovieDetailsModule;
import com.psimao.themovieapp.presentation.popularmovies.PopularMoviesActivity;
import com.psimao.themovieapp.presentation.popularmovies.PopularMoviesModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {PopularMoviesModule.class})
    abstract PopularMoviesActivity providePopularMoviesActivity();

    @ContributesAndroidInjector(modules = {MovieDetailsModule.class})
    abstract MovieDetailsActivity provideMovieDetailsActivity();
}
