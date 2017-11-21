package com.psimao.themovieapp.storage.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.psimao.themovieapp.data.entity.UserPreferences;
import com.psimao.themovieapp.storage.PreferencesStorage;
import com.psimao.themovieapp.storage.UserPreferencesStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(PreferencesStorage.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    static PreferencesStorage<UserPreferences> provideUserPreferencesStorage(SharedPreferences sharedPreferences) {
        return new UserPreferencesStorage(sharedPreferences);
    }
}
