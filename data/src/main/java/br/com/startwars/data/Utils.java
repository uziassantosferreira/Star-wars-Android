package br.com.startwars.data;

import android.content.Context;

import java.math.BigInteger;
import java.security.SecureRandom;

import br.com.startwars.data.api.ApiClient;
import br.com.startwars.data.api.ApiMovieClient;
import io.realm.Realm;

/**
 * Created by Uzias on 18/01/17.
 */

public class Utils {

    public static void initApiUrls(ApiClient.UrlProvider provider) {
        ApiClient.initUrls(provider);
    }

    public static void initApiMovieUrls(ApiMovieClient.UrlProvider provider) {
        ApiMovieClient.initUrls(provider);
    }

    public static void initRealm(Context context) {
        Realm.init(context);
    }

    public static long ReplaceStringToNumbers(String url) {
        return Long.valueOf(url.replaceAll("\\D+",""));
    }

    public static String getRandomFileName() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
