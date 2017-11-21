package com.psimao.themovieapp.application.di;

import android.app.Application;

import com.psimao.themovieapp.application.CustomApplication;
import com.psimao.themovieapp.data.repository.di.RepositoryModule;
import com.psimao.themovieapp.storage.di.StorageModule;
import com.psimao.themovieapp.webservice.di.WebServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AppModule.class,
                WebServiceModule.class,
                RepositoryModule.class,
                ActivityBuilder.class,
                StorageModule.class
        }
)
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(CustomApplication application);
}
