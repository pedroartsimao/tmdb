package com.psimao.themovieapp.data.entity;

import java.util.List;

public class UserPreferences {

    private List<Long> favoriteMovies;

    public List<Long> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<Long> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }
}
