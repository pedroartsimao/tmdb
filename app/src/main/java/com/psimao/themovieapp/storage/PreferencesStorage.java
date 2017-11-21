package com.psimao.themovieapp.storage;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PreferencesStorage<T> {

    String PREFERENCES_NAME = "tmdb_preferences";

    Single<T> get();

    Completable put(T data);

    Completable delete();
}
