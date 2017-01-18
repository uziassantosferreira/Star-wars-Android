package br.com.startwars.data.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Uzias on 18/01/17.
 */

public class ApiErrors implements Serializable {

    @SerializedName("errors")
    public ArrayList<String> errors;
}
