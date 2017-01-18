package br.com.starwars.listcharacters.di;

import br.com.starwars.di.PerActivity;
import br.com.starwars.listcharacters.ListCharactersActivity;
import dagger.Subcomponent;

/**
 * Created by Uzias on 17/01/17.
 */

@PerActivity
@Subcomponent(modules = ListCharactersModule.class)
public interface ListCharactersComponent {

    void inject(ListCharactersActivity activity);
}
