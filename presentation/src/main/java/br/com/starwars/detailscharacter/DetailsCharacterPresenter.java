package br.com.starwars.detailscharacter;

import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.domain.repositories.CharacterRepository;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterPresenter implements DetailsCharacterContract.Presenter {

    private CharacterRepository characterRepository;
    private SchedulerProvider schedulerProvider;
    private DetailsCharacterContract.View view;

    public DetailsCharacterPresenter(CharacterRepository characterRepository, SchedulerProvider schedulerProvider) {
        this.characterRepository = characterRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void onViewCreated() {
        view.showProgressDialog();
        characterRepository.getCharacterByUrl("http://swapi.co/api/people/1/")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Character>() {
                    @Override
                    public void onSuccess(Character value) {
                        view.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //TODO
                        e.getMessage();
                        view.hideProgressDialog();
                    }
                });
    }

    @Override
    public void setView(DetailsCharacterContract.View view) {
        this.view = view;
    }
}
