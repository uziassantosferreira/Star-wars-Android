package br.com.starwars.detailscharacter;


/**
 * Created by Uzias on 20/01/17.
 */

public interface DetailsCharacterContract {

    interface View {

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter {
        void onViewCreated();
        void setView(DetailsCharacterContract.View view);
    }

}
