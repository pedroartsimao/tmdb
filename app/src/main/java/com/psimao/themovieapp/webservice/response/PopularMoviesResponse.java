package com.psimao.themovieapp.webservice.response;

import com.google.gson.annotations.SerializedName;
import com.psimao.themovieapp.data.entity.PopularMovies;

import java.util.List;

public class PopularMoviesResponse {

    @SerializedName("page")
    Integer page;
    @SerializedName("total_results")
    Integer totalResults;
    @SerializedName("total_pages")
    Integer totalPages;
    @SerializedName("results")
    List<PopularMovies> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<PopularMovies> getResults() {
        return results;
    }

    public void setResults(List<PopularMovies> results) {
        this.results = results;
    }
}
