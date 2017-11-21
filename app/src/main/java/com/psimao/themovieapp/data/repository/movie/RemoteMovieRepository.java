package com.psimao.themovieapp.data.repository.movie;

import com.psimao.themovieapp.data.entity.MovieDetails;
import com.psimao.themovieapp.data.entity.PopularMovies;
import com.psimao.themovieapp.webservice.TmdbWebService;
import com.psimao.themovieapp.webservice.response.PopularMoviesResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemoteMovieRepository implements MovieRepository {

    private TmdbWebService ws;

    @Inject
    public RemoteMovieRepository(TmdbWebService ws) {
        this.ws = ws;
    }

    @Override
    public Single<List<PopularMovies>> popularMovies(int page) {
        return ws.getPopularMovies(page)
                .map(PopularMoviesResponse::getResults);
    }

    @Override
    public Single<MovieDetails> movieDetails(long movieId) {
        return ws.getMovieDetails(movieId)
                .map(movieDetailsResponse -> (MovieDetails) movieDetailsResponse);
    }
}
