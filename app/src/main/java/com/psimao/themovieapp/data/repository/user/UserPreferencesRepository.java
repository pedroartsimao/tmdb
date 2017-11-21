package com.psimao.themovieapp.data.repository.user;

import com.psimao.themovieapp.data.entity.UserPreferences;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserPreferencesRepository {

    Single<UserPreferences> getUserPreferences();

    Completable putFavoriteMovie(Long movieId);

    Completable removeFavoriteMovie(Long movieId);
}
