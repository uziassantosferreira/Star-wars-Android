package br.com.starwars.film.di;


import br.com.starwars.di.PerActivity;
import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.film.FilmContract;
import br.com.starwars.film.FilmPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Uzias on 20/01/17.
 */

@Module
public class FilmModule {

    @PerActivity
    @Provides
    FilmContract.Presenter providePresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        return new FilmPresenter(charactersUseCase, schedulerProvider);
    }


}
