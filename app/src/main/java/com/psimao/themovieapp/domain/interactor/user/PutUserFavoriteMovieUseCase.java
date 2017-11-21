package com.psimao.themovieapp.domain.interactor.user;

import com.psimao.themovieapp.data.repository.user.UserPreferencesRepository;
import com.psimao.themovieapp.domain.interactor.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PutUserFavoriteMovieUseCase extends BaseUseCase<Void, Long> {

    private UserPreferencesRepository repository;

    @Inject
    public PutUserFavoriteMovieUseCase(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Void> createObservable(Long movieId) {
        return repository.putFavoriteMovie(movieId)
                .toObservable();
    }
}
