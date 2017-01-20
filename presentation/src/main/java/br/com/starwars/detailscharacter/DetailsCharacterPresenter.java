package br.com.starwars.detailscharacter;

import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.domain.repositories.CharacterRepository;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterPresenter implements DetailsCharacterContract.Presenter {

    private CharacterRepository characterRepository;
    private SchedulerProvider schedulerProvider;

    public DetailsCharacterPresenter(CharacterRepository characterRepository, SchedulerProvider schedulerProvider) {
        this.characterRepository = characterRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void setView(DetailsCharacterContract.View view) {

    }
}
