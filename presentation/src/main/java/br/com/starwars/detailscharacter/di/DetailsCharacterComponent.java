package br.com.starwars.detailscharacter.di;

import br.com.starwars.detailscharacter.DetailsCharacterActivity;
import br.com.starwars.di.PerActivity;
import br.com.starwars.listcharacters.ListCharactersActivity;
import dagger.Subcomponent;

/**
 * Created by Uzias on 17/01/17.
 */

@PerActivity
@Subcomponent(modules = DetailsCharacterModule.class)
public interface DetailsCharacterComponent {

    void inject(DetailsCharacterActivity activity);
}
