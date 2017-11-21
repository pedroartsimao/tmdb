package com.psimao.themovieapp.presentation.moviedetails;

import com.psimao.themovieapp.application.di.qualifiers.IOScheduler;
import com.psimao.themovieapp.application.di.qualifiers.UIScheduler;
import com.psimao.themovieapp.data.repository.movie.MovieRepository;
import com.psimao.themovieapp.data.repository.user.UserPreferencesRepository;
import com.psimao.themovieapp.domain.interactor.movies.GetMovieDetailsUseCase;
import com.psimao.themovieapp.domain.interactor.user.IsAnUserFavoriteMovieUseCase;
import com.psimao.themovieapp.domain.interactor.user.PutUserFavoriteMovieUseCase;
import com.psimao.themovieapp.domain.interactor.user.RemoveUserFavoriteMovieUseCase;
import com.psimao.themovieapp.domain.mapper.movies.MovieDetailsMapper;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class MovieDetailsModule {

    @Provides
    static MovieDetailsContract.View provideView(MovieDetailsActivity activity) {
        return activity;
    }

    @Provides
    static MovieDetailsContract.Presenter providePresenter(
            MovieDetailsContract.View view,
            GetMovieDetailsUseCase<MovieDetailsViewModel> movieDetailsUseCase,
            IsAnUserFavoriteMovieUseCase favoriteMovieUseCase,
            PutUserFavoriteMovieUseCase putUserFavoriteMovieUseCase,
            RemoveUserFavoriteMovieUseCase removeUserFavoriteMovieUseCase,
            @IOScheduler Scheduler subscriptionScheduler,
            @UIScheduler Scheduler observerScheduler) {
        return new MovieDetailsPresenter(
                view,
                movieDetailsUseCase,
                favoriteMovieUseCase,
                putUserFavoriteMovieUseCase,
                removeUserFavoriteMovieUseCase,
                subscriptionScheduler,
                observerScheduler);
    }

    @Provides
    static GetMovieDetailsUseCase<MovieDetailsViewModel> provideMovieDetailsUseCase(
            MovieRepository movieRepository,
            MovieDetailsMapper mapper) {
        return new GetMovieDetailsUseCase<>(movieRepository, mapper);
    }

    @Provides
    static IsAnUserFavoriteMovieUseCase provideFavoriteMovieUseCase(
            UserPreferencesRepository repository) {
        return new IsAnUserFavoriteMovieUseCase(repository);
    }

    @Provides
    static PutUserFavoriteMovieUseCase providePutUserFavoriteMovieUseCase(
            UserPreferencesRepository repository) {
        return new PutUserFavoriteMovieUseCase(repository);
    }

    @Provides
    static RemoveUserFavoriteMovieUseCase provideRemoveUserFavoriteMovieUseCase(
            UserPreferencesRepository repository) {
        return new RemoveUserFavoriteMovieUseCase(repository);
    }

    @Provides
    static MovieDetailsMapper provideMapper() {
        return new MovieDetailsMapper();
    }
}
