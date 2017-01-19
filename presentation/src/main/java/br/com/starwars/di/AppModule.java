package br.com.starwars.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.navigation.Navigator;
import br.com.starwars.utils.ThreadUtil;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Uzias on 17/01/17.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Singleton
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new ThreadUtil();
    }
}
