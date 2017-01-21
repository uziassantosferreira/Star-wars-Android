package br.com.starwars.detailscharacter.di;


import android.support.v4.app.FragmentManager;

import br.com.starwars.detailscharacter.DetailsCharacterContract;
import br.com.starwars.detailscharacter.DetailsCharacterFragmentPagerAdapter;
import br.com.starwars.detailscharacter.DetailsCharacterPresenter;
import br.com.starwars.di.PerActivity;
import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.providers.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Uzias on 17/01/17.
 */

@Module
public class DetailsCharacterModule {

    private final FragmentManager fm;

    public DetailsCharacterModule(FragmentManager fm) {
        this.fm = fm;
    }

    @PerActivity
    @Provides
    DetailsCharacterContract.Presenter providePresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        return new DetailsCharacterPresenter(charactersUseCase, schedulerProvider);
    }

    @PerActivity
    @Provides
    DetailsCharacterFragmentPagerAdapter provideDetailsCharacterFragmentPagerAdapter() {
        return new DetailsCharacterFragmentPagerAdapter(fm);
    }

}
