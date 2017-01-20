package br.com.starwars.di;

import javax.inject.Singleton;

import br.com.starwars.StartWarsApplication;
import br.com.starwars.detailscharacter.di.DetailsCharacterComponent;
import br.com.starwars.detailscharacter.di.DetailsCharacterModule;
import br.com.starwars.listcharacters.di.ListCharactersComponent;
import br.com.starwars.listcharacters.di.ListCharactersModule;
import dagger.Component;

/**
 * Created by Uzias on 17/01/17.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(StartWarsApplication startWarsApplication);

    ListCharactersComponent plus(ListCharactersModule listCharactersModule);

    DetailsCharacterComponent plus(DetailsCharacterModule detailsCharacterModule);
}
