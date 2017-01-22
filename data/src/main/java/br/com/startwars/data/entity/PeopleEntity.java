package br.com.startwars.data.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Uzias on 18/01/17.
 */

public class PeopleEntity extends RealmObject {

    private String name;

    @PrimaryKey
    private String url;

    private String mass;

    private String hairColor;

    private String skinColor;

    private String eyeColor;

    private String birthYear;

    private String gender;

    private String height;

    private String films;

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFilms() {
        List<String> list;
        if(films != null) {
            list = Collections.unmodifiableList(new ArrayList<>((Arrays.asList(films.split(",")))));
        } else {
            list = Collections.emptyList();
        }
        return list;
    }

    public void setFilms(List<String> films) {
        if(films == null || films.isEmpty()) {
            this.films = null;
        } else {
            this.films = "";
            for (String film: films){
                this.films += film + ",";
            }
            this.films = this.films.substring(0, this.films.length() - 1);
        }
    }
}
