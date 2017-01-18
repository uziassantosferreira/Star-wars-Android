package br.com.starwars.di;

import javax.inject.Singleton;

import br.com.starwars.StartWarsApplication;
import dagger.Component;

/**
 * Created by Uzias on 17/01/17.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(StartWarsApplication startWarsApplication);
}
