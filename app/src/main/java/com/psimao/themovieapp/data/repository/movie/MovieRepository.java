package com.psimao.themovieapp.data.repository.movie;

import com.psimao.themovieapp.data.entity.MovieDetails;
import com.psimao.themovieapp.data.entity.PopularMovies;

import java.util.List;

import io.reactivex.Single;

public interface MovieRepository {

    Single<List<PopularMovies>> popularMovies(int page);

    Single<MovieDetails> movieDetails(long movieId);
}
