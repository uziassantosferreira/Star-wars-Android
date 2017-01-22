package br.com.starwars;

import android.app.Application;

import br.com.startwars.data.Utils;
import br.com.starwars.di.AppComponent;
import br.com.starwars.di.AppModule;
import br.com.starwars.di.DaggerAppComponent;
import io.realm.Realm;

/**
 * Created by Uzias on 17/01/17.
 */

public class StartWarsApplication extends Application {

    private static StartWarsApplication instance;

    private AppComponent applicationComponent;

    public static StartWarsApplication getInstance() {
        return instance;
    }

    public static void setInstance(StartWarsApplication instance) {
        StartWarsApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setInstance(this);
        applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();

        Utils.initApiUrls(() -> BuildConfig.API_ENDPOINT);
        Utils.initRealm(this);
        Utils.initApiUrls(() -> BuildConfig.API_MOVIE_ENDPOINT);
    }

    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
