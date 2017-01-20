package br.com.starwars.listcharacters;


import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import br.com.startwars.data.repositories.CharacterDataRepository;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.domain.repositories.CharacterRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersPresenter implements ListCharactersContract.Presenter {

    private ListCharactersContract.View view;
    private CharacterRepository characterRepository;
    private SchedulerProvider schedulerProvider;

    public ListCharactersPresenter(CharacterRepository characterRepository, SchedulerProvider schedulerProvider) {
        this.characterRepository = characterRepository;
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
        characterRepository.getCharacterByUrl(url)
                .subscribeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Character>() {
            @Override
            public void onSuccess(Character value) {
                view.hideProgressDialog();
                getListCharacters();
            }

            @Override
            public void onError(Throwable e) {
                //TODO
                view.hideProgressDialog();
            }
        });
    }

    @Override
    public void onViewCreated() {
        view.setupView();
        getListCharacters();

    }

    private void getListCharacters(){
        characterRepository.getListCharacters()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<List<Character>>() {
                    @Override
                    public void onSuccess(List<Character> list) {
                        view.setListAndNotifyAdaper(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //TODO
                    }
                });
    }
}
