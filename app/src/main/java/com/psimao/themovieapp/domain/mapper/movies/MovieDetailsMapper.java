package com.psimao.themovieapp.domain.mapper.movies;

import com.psimao.themovieapp.data.entity.Genre;
import com.psimao.themovieapp.data.entity.MovieDetails;
import com.psimao.themovieapp.domain.mapper.BaseMapper;
import com.psimao.themovieapp.domain.mapper.movies.util.MoviesMapperUtils;
import com.psimao.themovieapp.presentation.moviedetails.MovieDetailsViewModel;
import com.psimao.themovieapp.util.DateUtils;

import java.util.List;

public class MovieDetailsMapper extends BaseMapper<MovieDetails, MovieDetailsViewModel> {

    @Override
    public MovieDetailsViewModel transform(MovieDetails movieDetails) {
        MovieDetailsViewModel model = new MovieDetailsViewModel();
        model.setTitle(movieDetails.getTitle());
        model.setImageUrl(extractImageUrlFromEntity(movieDetails));
        model.setRuntime(DateUtils.formatMinutesToHoursAndMinutes(movieDetails.getRuntime()));
        model.setGenres(formatGenres(movieDetails.getGenres()));
        model.setReleaseDate(DateUtils.formatDateToString(movieDetails.getReleaseDate()));
        model.setOverview(movieDetails.getOverview().trim());
        model.setVoteAverage(movieDetails.getVoteAverage());
        model.setVoteCount(movieDetails.getVoteCount());
        model.setHomepage(formatHomepage(movieDetails.getHomepage()));
        return model;
    }

    private String extractImageUrlFromEntity(MovieDetails movieDetails) {
        String imagePath = null;
        String imageSize = null;
        if (movieDetails.getBackdropPath() != null && !movieDetails.getBackdropPath().isEmpty()) {
            imagePath = movieDetails.getBackdropPath();
            imageSize = MoviesMapperUtils.BACKDROP_SIZE_LARGE;
        } else if (movieDetails.getPosterPath() != null && !movieDetails.getPosterPath().isEmpty()) {
            imagePath = movieDetails.getPosterPath();
            imageSize = MoviesMapperUtils.POSTER_SIZE_LARGE;
        }
        return imagePath != null ? MoviesMapperUtils.formatImageUrl(imageSize, imagePath) : null;
    }

    private String formatGenres(List<Genre> genres) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre : genres) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(genre.getName());
        }
        return stringBuilder.toString();
    }

    private String formatHomepage(String homepage) {
        String formatedHomepage = homepage;
        if (homepage != null && !homepage.isEmpty() && homepage.lastIndexOf('/') == homepage.length() - 1) {
            formatedHomepage = homepage.substring(0, homepage.length() - 1);
        }
        return formatedHomepage;
    }
}
