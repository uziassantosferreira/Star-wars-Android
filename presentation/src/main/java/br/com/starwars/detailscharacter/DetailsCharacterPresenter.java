package br.com.starwars.detailscharacter;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterPresenter implements DetailsCharacterContract.Presenter {

    private CharactersUseCase charactersUseCase;
    private SchedulerProvider schedulerProvider;
    private DetailsCharacterContract.View view;
    private String url;

    public DetailsCharacterPresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        this.charactersUseCase = charactersUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void onViewCreated() {
        url = view.getUrlInIntent();
        if (url == null){
            view.finishActivity();
        }else {
            getCharacter();
        }
    }

    @Override
    public void setView(DetailsCharacterContract.View view) {
        this.view = view;
    }

    private void getCharacter() {
        view.showProgressDialog();
        charactersUseCase.getCharacterByUrl(url)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Character>() {
                    @Override
                    public void onSuccess(Character character) {
                        view.setFields(character.getName(), character.getUrl(), character.getHeight(),
                                character.getMass(), character.getHairColor(), character.getSkinColor(),
                                character.getEyeColor(), character.getBirthYear(), character.getGender());
                        view.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.hideProgressDialog();
                        view.showGenericError();
                    }
                });
    }

}
