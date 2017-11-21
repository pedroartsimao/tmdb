package com.psimao.themovieapp.domain.interactor.user;

import com.psimao.themovieapp.data.entity.UserPreferences;
import com.psimao.themovieapp.data.repository.user.UserPreferencesRepository;
import com.psimao.themovieapp.domain.interactor.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class IsAnUserFavoriteMovieUseCase extends BaseUseCase<Boolean, Long> {

    private UserPreferencesRepository repository;

    @Inject
    public IsAnUserFavoriteMovieUseCase(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Boolean> createObservable(Long movieId) {
        return repository.getUserPreferences()
                .toObservable()
                .map(preferences -> isAnUserFavoriteMovie(movieId, preferences));
    }

    private Boolean isAnUserFavoriteMovie(Long movieId, UserPreferences preferences) {
        return preferences.getFavoriteMovies() != null
                && preferences.getFavoriteMovies().contains(movieId);
    }
}
