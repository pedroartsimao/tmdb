package com.psimao.themovieapp.domain.interactor.user;

import com.psimao.themovieapp.data.repository.user.UserPreferencesRepository;
import com.psimao.themovieapp.domain.interactor.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoveUserFavoriteMovieUseCase extends BaseUseCase<Void, Long> {

    private UserPreferencesRepository repository;

    @Inject
    public RemoveUserFavoriteMovieUseCase(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Void> createObservable(Long movieId) {
        return repository.removeFavoriteMovie(movieId)
                .toObservable();
    }
}