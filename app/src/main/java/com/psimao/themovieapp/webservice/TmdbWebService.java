package com.psimao.themovieapp.webservice;

import com.psimao.themovieapp.webservice.response.MovieDetailsResponse;
import com.psimao.themovieapp.webservice.response.PopularMoviesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbWebService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String AUTHENTICATION_KEY = "api_key";

    @GET("movie/popular")
    Single<PopularMoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/{movie_id}")
    Single<MovieDetailsResponse> getMovieDetails(@Path("movie_id") long movieId);
}
