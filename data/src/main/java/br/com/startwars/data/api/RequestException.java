package br.com.startwars.data.api;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;

/**
 * Created by Uzias on 18/01/17.
 */

public class RequestException extends Exception {
    private static final long serialVersionUID = -4934306561398902688L;

    private static final int SERVER_ERROR_CODE = 500;
    private static final int UNAUTHORIZED_CODE = 401;

    private int code;
    private boolean networkError;
    private boolean serverError;
    private ApiErrors errors;


    public RequestException(int code, ResponseBody errorBody) {
        this.code = code;
        try {
            errors = new Gson().fromJson(errorBody.string(), ApiErrors.class);
        } catch (IOException e) {
            //errors gone wrong
        }
    }

    public RequestException(final Throwable cause) {
        super(cause);
        if (cause instanceof IOException) {
            networkError = true;
        } else {
            serverError = true;
        }
    }

    public boolean isBadRequest() {
        return code > UNAUTHORIZED_CODE && code < SERVER_ERROR_CODE;
    }

    public boolean isUnauthorized() {
        return code == UNAUTHORIZED_CODE;
    }

    public boolean isNetworkError() {
        return networkError;
    }

    public boolean isServerError() {
        return serverError;
    }

    public ArrayList<String> getErrorMessages() {
        return errors.errors;
    }
}