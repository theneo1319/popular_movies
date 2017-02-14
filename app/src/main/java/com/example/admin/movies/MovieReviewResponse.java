package com.example.admin.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 23-01-2017.
 */

public class MovieReviewResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<MovieReview> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<MovieReview> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(List<MovieReview> results) {
        this.results = results;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
