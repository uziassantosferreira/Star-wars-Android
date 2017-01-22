package br.com.startwars.data.entity;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Uzias on 18/01/17.
 */

public class MovieEntity extends RealmObject {

    private String posterPath;

    @PrimaryKey
    private String name;

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
}
