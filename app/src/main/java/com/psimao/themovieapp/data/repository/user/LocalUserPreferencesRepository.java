package com.psimao.themovieapp.data.repository.user;

import com.psimao.themovieapp.data.entity.UserPreferences;
import com.psimao.themovieapp.storage.PreferencesStorage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LocalUserPreferencesRepository implements UserPreferencesRepository {

    private PreferencesStorage<UserPreferences> preferencesStorage;

    @Inject
    public LocalUserPreferencesRepository(PreferencesStorage<UserPreferences> preferencesStorage) {
        this.preferencesStorage = preferencesStorage;
    }

    @Override
    public Single<UserPreferences> getUserPreferences() {
        return preferencesStorage.get();
    }

    @Override
    public Completable putFavoriteMovie(Long movieId) {
        return preferencesStorage.get()
                .map(preferences -> {
                    List<Long> favoriteMovies = preferences.getFavoriteMovies();
                    if( favoriteMovies == null) {
                        favoriteMovies = new ArrayList<>();
                    }
                    if (!favoriteMovies.contains(movieId)) {
                        favoriteMovies.add(movieId);
                        preferences.setFavoriteMovies(favoriteMovies);
                    }
                    return preferences;
                })
                .doOnSuccess(preferences -> preferencesStorage.put(preferences).subscribe())
                .toCompletable();
    }

    @Override
    public Completable removeFavoriteMovie(Long movieId) {
        return preferencesStorage.get()
                .map(preferences -> {
                    List<Long> favoriteMovies = preferences.getFavoriteMovies();
                    if (favoriteMovies!= null && favoriteMovies.contains(movieId)) {
                        favoriteMovies.remove(movieId);
                        preferences.setFavoriteMovies(favoriteMovies);
                    }
                    return preferences;
                })
                .doOnSuccess(preferences -> preferencesStorage.put(preferences).subscribe())
                .toCompletable();
    }
}