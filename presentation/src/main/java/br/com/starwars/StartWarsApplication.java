package br.com.starwars;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

import br.com.startwars.data.Utils;
import br.com.startwars.data.api.ApiMovieClient;
import br.com.starwars.di.AppComponent;
import br.com.starwars.di.AppModule;
import br.com.starwars.di.DaggerAppComponent;


/**
 * Created by Uzias on 17/01/17.
 */

public class StartWarsApplication extends Application {

    private static StartWarsApplication instance;

    private AppComponent applicationComponent;

    @Inject
    ApiMovieClient.UrlProvider urlProvider;

    public static StartWarsApplication getInstance() {
        return instance;
    }

    public static void setInstance(StartWarsApplication instance) {
        StartWarsApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        setInstance(this);
        applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
        applicationComponent.inject(this);

        Utils.initApiUrls(() -> BuildConfig.API_ENDPOINT);
        Utils.initApiMovieUrls(urlProvider);
        Utils.initRealm(this);

    }

    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
