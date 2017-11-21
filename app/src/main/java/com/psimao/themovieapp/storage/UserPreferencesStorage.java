package com.psimao.themovieapp.storage;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.psimao.themovieapp.data.entity.UserPreferences;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserPreferencesStorage implements PreferencesStorage<UserPreferences> {

    private static final String KEY_USER_PREFERENCES = "user_preferences";

    SharedPreferences sharedPreferences;

    public UserPreferencesStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Single<UserPreferences> get() {
        return Single.fromCallable(() -> {
            UserPreferences preferences = new UserPreferences();
            String preferencesString = sharedPreferences.getString(KEY_USER_PREFERENCES, "");
            if (!preferencesString.isEmpty()) {
                preferences = new Gson().fromJson(preferencesString, UserPreferences.class);
            }
            return preferences;
        });
    }

    @Override
    public Completable put(UserPreferences data) {
        return Completable.fromCallable(() -> {
            sharedPreferences.edit()
                    .putString(KEY_USER_PREFERENCES, new Gson().toJson(data))
                    .apply();
            return new Object();
        });
    }

    @Override
    public Completable delete() {
        return Completable.fromCallable(() -> {
            sharedPreferences.edit()
                    .remove(KEY_USER_PREFERENCES)
                    .apply();
            return new Object();
        });
    }
}
