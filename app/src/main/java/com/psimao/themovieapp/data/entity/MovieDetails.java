package com.psimao.themovieapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails extends Movie {

    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("status")
    private String status;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("budget")
    private Double budget;
    @SerializedName("revenue")
    private Double revenue;
    @SerializedName("genres")
    private List<Genre> genres;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
