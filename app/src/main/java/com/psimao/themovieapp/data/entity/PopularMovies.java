package com.psimao.themovieapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMovies extends Movie {

    @SerializedName("genre_ids")
    List<Long> genreIds;

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }
}
