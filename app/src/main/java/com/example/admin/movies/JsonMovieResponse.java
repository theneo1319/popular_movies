package com.example.admin.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 30-12-2016.
 */

public class JsonMovieResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<MovieData> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieData> getResults() {
        return results;
    }

    public void setResults(List<MovieData> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
