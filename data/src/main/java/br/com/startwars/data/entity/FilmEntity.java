package br.com.startwars.data.entity;

import com.google.gson.annotations.SerializedName;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Uzias on 18/01/17.
 */

public class FilmEntity extends RealmObject{

    private String title;

    @PrimaryKey
    private String url;

    private String director;

    private String producer;

    @SerializedName("opening_crawl")
    private String openingCrawl;

    @SerializedName("release_date")
    public String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
