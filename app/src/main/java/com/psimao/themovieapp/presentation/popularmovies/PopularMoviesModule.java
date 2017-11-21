package com.psimao.themovieapp.presentation.popularmovies;

import com.psimao.themovieapp.application.di.qualifiers.IOScheduler;
import com.psimao.themovieapp.application.di.qualifiers.UIScheduler;
import com.psimao.themovieapp.data.repository.movie.MovieRepository;
import com.psimao.themovieapp.domain.interactor.movies.GetPopularMoviesUseCase;
import com.psimao.themovieapp.domain.mapper.movies.PopularMoviesMapper;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class PopularMoviesModule {

    @Provides
    static PopularMoviesContract.View provideView(PopularMoviesActivity activity) {
        return activity;
    }

    @Provides
    static PopularMoviesContract.Presenter providePresenter(
            PopularMoviesContract.View view,
            GetPopularMoviesUseCase<PopularMoviesViewModel> useCase,
            @IOScheduler Scheduler subscriptionScheduler,
            @UIScheduler Scheduler observerScheduler) {
        return new PopularMoviesPresenter(view, useCase, subscriptionScheduler, observerScheduler);
    }

    @Provides
    static GetPopularMoviesUseCase<PopularMoviesViewModel> provideUseCase(
            MovieRepository movieRepository,
            PopularMoviesMapper mapper) {
        return new GetPopularMoviesUseCase<>(movieRepository, mapper);
    }

    @Provides
    static PopularMoviesMapper provideMapper() {
        return new PopularMoviesMapper();
    }
}
