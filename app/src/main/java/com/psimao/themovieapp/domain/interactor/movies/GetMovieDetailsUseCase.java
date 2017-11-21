package com.psimao.themovieapp.domain.interactor.movies;

import com.psimao.themovieapp.data.entity.MovieDetails;
import com.psimao.themovieapp.data.repository.movie.MovieRepository;
import com.psimao.themovieapp.domain.interactor.BaseUseCase;
import com.psimao.themovieapp.domain.mapper.BaseMapper;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetailsUseCase<T> extends BaseUseCase<T, Long> {

    private MovieRepository remoteRepository;
    private BaseMapper<MovieDetails, T> mapper;

    @Inject
    public GetMovieDetailsUseCase(MovieRepository remoteRepository, BaseMapper<MovieDetails, T> mapper) {
        this.remoteRepository = remoteRepository;
        this.mapper = mapper;
    }

    @Override
    public Observable<T> createObservable(Long movieId) {
        return remoteRepository.movieDetails(movieId)
                .toObservable()
                .map(movieDetails -> mapper.transform(movieDetails));
    }
}
