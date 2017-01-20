package br.com.starwars.detailscharacter;


/**
 * Created by Uzias on 20/01/17.
 */

public interface DetailsCharacterContract {

    interface View {

        void showProgressDialog();

        void hideProgressDialog();

        void setFields(String name, String url, String height, String mass, String hairColor,
                       String skinColor, String eyeColor, String birthYear, String gender);

        String getUrlInIntent();

        void finishActivity();
    }

    interface Presenter {
        void onViewCreated();
        void setView(DetailsCharacterContract.View view);
    }

}
