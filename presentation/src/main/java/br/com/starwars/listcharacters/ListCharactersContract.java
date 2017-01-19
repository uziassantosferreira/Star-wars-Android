package br.com.starwars.listcharacters;

import java.util.List;

import br.com.starwars.domain.models.Character;

/**
 * Created by Uzias on 17/01/17.
 */

public interface ListCharactersContract {

    interface View {

        void setupView();

        void setListAndNotifyAdaper(List<Character> list);
    }

    interface Presenter {

        void onViewCreated();
        void setView(View view);
    }
}
