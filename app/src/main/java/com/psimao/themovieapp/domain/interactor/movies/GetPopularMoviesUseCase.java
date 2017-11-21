package com.psimao.themovieapp.domain.interactor.movies;

import com.psimao.themovieapp.data.entity.PopularMovies;
import com.psimao.themovieapp.data.repository.movie.MovieRepository;
import com.psimao.themovieapp.domain.interactor.BaseUseCase;
import com.psimao.themovieapp.domain.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPopularMoviesUseCase<T> extends BaseUseCase<List<T>, Integer> {

    private MovieRepository remoteRepository;
    private BaseMapper<PopularMovies, T> mapper;

    @Inject
    public GetPopularMoviesUseCase(MovieRepository remoteRepository, BaseMapper<PopularMovies, T> mapper) {
        this.remoteRepository = remoteRepository;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<T>> createObservable(Integer page) {
        return remoteRepository.popularMovies(page)
                .toObservable()
                .map(popularMovies -> new ArrayList<>(mapper.transform(popularMovies)));
    }
}
