package com.psimao.themovieapp.domain.mapper.movies;

import com.psimao.themovieapp.data.entity.PopularMovies;
import com.psimao.themovieapp.domain.mapper.BaseMapper;
import com.psimao.themovieapp.domain.mapper.movies.util.MoviesMapperUtils;
import com.psimao.themovieapp.presentation.popularmovies.PopularMoviesViewModel;

public class PopularMoviesMapper extends BaseMapper<PopularMovies, PopularMoviesViewModel> {

    @Override
    public PopularMoviesViewModel transform(PopularMovies popularMovies) {
        PopularMoviesViewModel model = new PopularMoviesViewModel();
        model.setId(popularMovies.getId());
        model.setTitle(popularMovies.getTitle());
        model.setImageUrl(extractImageUrlFromEntity(popularMovies));
        return model;
    }

    private String extractImageUrlFromEntity(PopularMovies popularMovies) {
        String imagePath = null;
        String imageSize = null;
        if (popularMovies.getPosterPath() != null && !popularMovies.getPosterPath().isEmpty()) {
            imagePath = popularMovies.getPosterPath();
            imageSize = MoviesMapperUtils.POSTER_SIZE_SMALL;
        } else if (popularMovies.getBackdropPath() != null && !popularMovies.getBackdropPath().isEmpty()) {
            imagePath = popularMovies.getBackdropPath();
            imageSize = MoviesMapperUtils.BACKDROP_SIZE_SMALL;
        }
        return imagePath != null ? MoviesMapperUtils.formatImageUrl(imageSize, imagePath) : null;
    }
}
