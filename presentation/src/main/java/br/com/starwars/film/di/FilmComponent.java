package br.com.starwars.film.di;

import br.com.starwars.di.PerActivity;
import br.com.starwars.film.FilmFragment;
import dagger.Subcomponent;

/**
 * Created by Uzias on 20/01/17.
 */

@PerActivity
@Subcomponent(modules = FilmModule.class)
public interface FilmComponent {

    void inject(FilmFragment fragment);
}
