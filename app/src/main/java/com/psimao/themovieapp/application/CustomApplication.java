package com.psimao.themovieapp.application;

import android.app.Activity;
import android.app.Application;

import com.psimao.themovieapp.application.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class CustomApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> injector;

    @Override
    public void onCreate() {
        super.onCreate();
        buildTopLevelDependenciesGraph();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return injector;
    }

    private void buildTopLevelDependenciesGraph() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
