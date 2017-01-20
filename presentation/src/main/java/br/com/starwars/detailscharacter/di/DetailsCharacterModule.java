package br.com.starwars.detailscharacter.di;

import br.com.startwars.data.mappers.CharacterMapper;
import br.com.startwars.data.repositories.CharacterDataRepository;
import br.com.startwars.data.store.PeopleCache;
import br.com.startwars.data.store.realm.RealmPeopleCache;
import br.com.starwars.detailscharacter.DetailsCharacterContract;
import br.com.starwars.detailscharacter.DetailsCharacterPresenter;
import br.com.starwars.di.PerActivity;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.domain.repositories.CharacterRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Uzias on 17/01/17.
 */

@Module
public class DetailsCharacterModule {

    @PerActivity
    @Provides
    DetailsCharacterContract.Presenter providePresenter(CharacterRepository characterRepository, SchedulerProvider schedulerProvider) {
        return new DetailsCharacterPresenter(characterRepository, schedulerProvider);
    }

    @PerActivity
    @Provides
    CharacterMapper provideCharacterMapper() {
        return new CharacterMapper();
    }

    @PerActivity
    @Provides
    PeopleCache providePeopleCache() {
        return new RealmPeopleCache();
    }

    @PerActivity
    @Provides
    CharacterRepository provideCharacterDataRepository(PeopleCache peopleCache, CharacterMapper characterMapper) {
        return new CharacterDataRepository(peopleCache, characterMapper);
    }

}
