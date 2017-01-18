package br.com.starwars.listcharacters;


import java.util.ArrayList;
import java.util.List;

import br.com.starwars.model.Character;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersPresenter implements ListCharactersContract.Presenter {

    private ListCharactersContract.View view;

    @Override
    public void setView(ListCharactersContract.View view){
        this.view = view;
    }
    @Override
    public void onViewCreated() {
        view.setupView();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            Character character = new Character();
            character.setName("Uzias " + i);
            character.setUrl("wwww.google.com.br");
            list.add(character);
        }
        view.setListAndNotifyAdaper(list);
    }
}
