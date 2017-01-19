package br.com.starwars.listcharacters;


import java.util.List;

import br.com.startwars.data.repositories.CharacterDataRepository;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersPresenter implements ListCharactersContract.Presenter {

    private ListCharactersContract.View view;
    private CharacterDataRepository characterDataRepository;
    private SchedulerProvider schedulerProvider;

    public ListCharactersPresenter(CharacterDataRepository characterDataRepository, SchedulerProvider schedulerProvider) {
        this.characterDataRepository = characterDataRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void setView(ListCharactersContract.View view){
        this.view = view;
    }
    @Override
    public void onViewCreated() {
        view.setupView();
        characterDataRepository.getListCharacters().subscribeOn(schedulerProvider.io()).subscribe(new DisposableSingleObserver<List<br.com.starwars.domain.models.Character>>() {
            @Override
            public void onSuccess(List<Character> list) {
                view.setListAndNotifyAdaper(list);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
