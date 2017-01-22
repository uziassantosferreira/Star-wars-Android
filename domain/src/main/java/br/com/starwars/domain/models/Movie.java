package br.com.starwars.domain.models;

/**
 * Created by Uzias on 22/01/17.
 */

public class Movie {

    private String posterPath;

    private String name;

    private boolean savedBitmapLocal;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSavedBitmapLocal() {
        return savedBitmapLocal;
    }

    public void setSavedBitmapLocal(boolean savedBitmapLocal) {
        this.savedBitmapLocal = savedBitmapLocal;
    }
}
