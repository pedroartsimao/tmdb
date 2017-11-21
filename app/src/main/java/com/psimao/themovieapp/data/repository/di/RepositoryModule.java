package com.psimao.themovieapp.data.repository.di;

import com.psimao.themovieapp.data.entity.UserPreferences;
import com.psimao.themovieapp.data.repository.movie.MovieRepository;
import com.psimao.themovieapp.data.repository.movie.RemoteMovieRepository;
import com.psimao.themovieapp.data.repository.user.LocalUserPreferencesRepository;
import com.psimao.themovieapp.data.repository.user.UserPreferencesRepository;
import com.psimao.themovieapp.storage.PreferencesStorage;
import com.psimao.themovieapp.webservice.TmdbWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    static MovieRepository provideMovieRepository(TmdbWebService ws) {
        return new RemoteMovieRepository(ws);
    }

    @Provides
    @Singleton
    static UserPreferencesRepository provideUserPreferencesRepository(PreferencesStorage<UserPreferences> preferencesStorage) {
        return new LocalUserPreferencesRepository(preferencesStorage);
    }
}
