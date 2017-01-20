package br.com.startwars.data;

import android.content.Context;

import br.com.startwars.data.api.ApiClient;
import io.realm.Realm;

/**
 * Created by Uzias on 18/01/17.
 */

public class Utils {

    public static void initApiUrls(ApiClient.UrlProvider provider) {
        ApiClient.initUrls(provider);
    }

    public static void initRealm(Context context) {
        Realm.init(context);
    }
}
