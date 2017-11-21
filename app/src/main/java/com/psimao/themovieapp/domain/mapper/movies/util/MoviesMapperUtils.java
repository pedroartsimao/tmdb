package com.psimao.themovieapp.domain.mapper.movies.util;

public final class MoviesMapperUtils {

    public static final String POSTER_SIZE_LARGE = "w780";
    public static final String POSTER_SIZE_SMALL = "w185";

    public static final String BACKDROP_SIZE_LARGE = "w1280";
    public static final String BACKDROP_SIZE_SMALL = "w300";

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/%s%s";

    public static String formatImageUrl(String imageSize, String imagePath) {
        return String.format(BASE_IMAGE_URL, imageSize, imagePath);
    }
}
