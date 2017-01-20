package br.com.starwars.listcharacters;


import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersPresenter implements ListCharactersContract.Presenter {

    private ListCharactersContract.View view;
    private CharactersUseCase charactersUseCase;
    private SchedulerProvider schedulerProvider;
    private List<Character> characters;

    public ListCharactersPresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        this.charactersUseCase = charactersUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void setView(ListCharactersContract.View view){
        this.view = view;
    }

    @Override
    public void clickedMenuItemQRCode() {
        view.openScanQRCode();
    }

    @Override
    public void barcodeScanned(Barcode barcode) {
        view.showProgressDialog();
        String url = barcode.displayValue;
        charactersUseCase.getCharacterByUrl(url)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Character>() {
            @Override
            public void onSuccess(Character value) {
                view.hideProgressDialog();
                getListCharacters();
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgressDialog();
                e.getMessage();
            }
        });
    }

    @Override
    public void clickedItemPosition(int position) {
        Character character = characters.get(position);
        view.goToDetailsCharacter(character.getUrl());
    }

    @Override
    public void onViewCreated() {
        view.setupView();
        getListCharacters();

    }

    private void getListCharacters(){
        charactersUseCase.getListCharacters()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<List<Character>>() {
                    @Override
                    public void onSuccess(List<Character> list) {
                        characters = list;
                        view.setListAndNotifyAdaper(characters);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //TODO
                        e.getMessage();
                        e.printStackTrace();
                    }
                });
    }
}
