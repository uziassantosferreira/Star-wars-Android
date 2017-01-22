package br.com.startwars.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Uzias on 22/01/17.
 */

public class ResponseMovieApiEntity {

    private int page;

    private List<MovieApiEntity> movieApiEntities;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieApiEntity> getMovieApiEntities() {
        return movieApiEntities;
    }

    public void setMovieApiEntities(List<MovieApiEntity> movieApiEntities) {
        this.movieApiEntities = movieApiEntities;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
