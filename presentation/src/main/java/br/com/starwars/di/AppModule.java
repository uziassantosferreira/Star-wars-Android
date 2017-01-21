package br.com.starwars.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.startwars.data.mappers.CharacterMapper;
import br.com.startwars.data.mappers.FilmMapper;
import br.com.startwars.data.repositories.CharacterDataRepository;
import br.com.startwars.data.store.FilmCache;
import br.com.startwars.data.store.PeopleCache;
import br.com.startwars.data.store.realm.RealmFilmCache;
import br.com.startwars.data.store.realm.RealmPeopleCache;
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
            , FilmCache filmCache, FilmMapper filmMapper) {
        return new CharacterDataRepository(peopleCache, characterMapper, filmCache, filmMapper);
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

}
