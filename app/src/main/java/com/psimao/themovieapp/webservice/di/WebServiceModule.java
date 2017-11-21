package com.psimao.themovieapp.webservice.di;

import android.util.Log;

import com.psimao.themovieapp.BuildConfig;
import com.psimao.themovieapp.webservice.TmdbWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServiceModule {

    @Provides
    @Singleton
    static TmdbWebService provideWebService(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(TmdbWebService.BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmdbWebService.class);
    }

    @Provides
    @Singleton
    static OkHttpClient provideHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    static Interceptor provideInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalUrl = originalRequest.url();

            HttpUrl url = originalUrl.newBuilder()
                    .addQueryParameter(TmdbWebService.AUTHENTICATION_KEY, BuildConfig.TMDB_KEY)
                    .build();
            Request request = originalRequest.newBuilder().url(url).build();

            long t1 = System.nanoTime();
            Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6, response.headers()));

            return response;
        };
    }

}
