package br.com.starwars.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.startwars.data.api.ApiMovieClient;
import br.com.startwars.data.mappers.CharacterMapper;
import br.com.startwars.data.mappers.FilmMapper;
import br.com.startwars.data.mappers.MovieMapper;
import br.com.startwars.data.repositories.CharacterDataRepository;
import br.com.startwars.data.store.FilmCache;
import br.com.startwars.data.store.MovieCache;
import br.com.startwars.data.store.PeopleCache;
import br.com.startwars.data.store.realm.RealmFilmCache;
import br.com.startwars.data.store.realm.RealmMovieCache;
import br.com.startwars.data.store.realm.RealmPeopleCache;
import br.com.starwars.BuildConfig;
import br.com.starwars.domain.executor.ThreadExecutor;
import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.domain.repositories.CharacterRepository;
import br.com.starwars.navigation.Navigator;
import br.com.starwars.utils.ThreadUtil;
import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    @Singleton
    @Provides
    CharacterRepository provideCharacterDataRepository(PeopleCache peopleCache, CharacterMapper characterMapper
            , FilmCache filmCache, FilmMapper filmMapper, MovieCache movieCache, MovieMapper movieMapper) {
        return new CharacterDataRepository(peopleCache, characterMapper, filmCache, filmMapper,
                movieCache, movieMapper);
    }

    @Singleton
    @Provides
    PeopleCache providePeopleCache() {
        return new RealmPeopleCache();
    }

    @Singleton
    @Provides
    CharacterMapper provideCharacterMapper() {
        return new CharacterMapper();
    }

    @Singleton
    @Provides
    MovieCache provideMovieCache() {
        return new RealmMovieCache();
    }

    @Singleton
    @Provides
    MovieMapper provideMovieMapper() {
        return new MovieMapper();
    }

    @Singleton
    @Provides
    FilmCache provideFilmCache() {
        return new RealmFilmCache();
    }

    @Singleton
    @Provides
    FilmMapper provideFilmMapper() {
        return new FilmMapper();
    }

    @Singleton
    @Provides
    CharactersUseCase provideCharactersUseCase(@Named("subscriberOn") final ThreadExecutor subscriberOn,
                                         @Named("observerOn") final ThreadExecutor observerOn,
                                         CharacterRepository characterRepository) {
        return new CharactersUseCase(subscriberOn, observerOn, characterRepository);
    }

    @Provides
    @Singleton
    @Named("subscriberOn")
    ThreadExecutor provideSubscriberOnThreadExecutor() {
        return new ThreadExecutor(Schedulers.newThread());
    }

    @Provides
    @Singleton
    @Named("observerOn")
    ThreadExecutor provideObserverOnExecutionThread() {
        return new ThreadExecutor(AndroidSchedulers.mainThread());
    }

    @Provides
    @Singleton
    ApiMovieClient.UrlProvider provideUrlProvider(){
        return new ApiMovieClient.UrlProvider() {
            @Override
            public String getApiEndpoint() {
                return BuildConfig.API_MOVIE_ENDPOINT;
            }

            @Override
            public String getApiKey() {
                return BuildConfig.MOVIE_API_KEY;
            }

            @Override
            public String getMovieBaseUrlImage() {
                return BuildConfig.MOVIE_API_BASE_URL_IMAGE;
            }
        };
    }

}
