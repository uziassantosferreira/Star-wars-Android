package br.com.starwars.detailscharacter;


/**
 * Created by Uzias on 20/01/17.
 */

public interface DetailsCharacterContract {

    interface View {

    }

    interface Presenter {
        void onViewCreated();
        void setView(DetailsCharacterContract.View view);
    }

}
