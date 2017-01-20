package br.com.starwars.listcharacters.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Singleton;

import br.com.startwars.data.mappers.CharacterMapper;
import br.com.startwars.data.repositories.CharacterDataRepository;
import br.com.startwars.data.store.PeopleCache;
import br.com.startwars.data.store.realm.RealmPeopleCache;
import br.com.starwars.di.PerActivity;
import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.domain.repositories.CharacterRepository;
import br.com.starwars.listcharacters.ListCharactersAdapter;
import br.com.starwars.listcharacters.ListCharactersContract;
import br.com.starwars.listcharacters.ListCharactersPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Uzias on 17/01/17.
 */

@Module
public class ListCharactersModule {

    @PerActivity
    @Provides
    ListCharactersAdapter provideListCharactersAdapter() {
        return new ListCharactersAdapter();
    }

    @PerActivity
    @Provides
    ListCharactersContract.Presenter providePresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        return new ListCharactersPresenter(charactersUseCase, schedulerProvider);
    }

    @PerActivity
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

}
