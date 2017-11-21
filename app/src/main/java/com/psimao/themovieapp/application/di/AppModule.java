package com.psimao.themovieapp.application.di;

import android.app.Application;
import android.content.Context;

import com.psimao.themovieapp.application.di.qualifiers.IOScheduler;
import com.psimao.themovieapp.application.di.qualifiers.UIScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
class AppModule {

    @Provides
    @Singleton
    static Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    @UIScheduler
    static Scheduler provideUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @IOScheduler
    static Scheduler provideIOScheduler() {
        return Schedulers.io();
    }
}
