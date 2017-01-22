package br.com.starwars.detailscharacter;


import java.util.List;

/**
 * Created by Uzias on 20/01/17.
 */

public interface DetailsCharacterContract {

    interface View {

        void showProgressDialog();

        void hideProgressDialog();

        void setFields(String name, String url, String height, String mass, String hairColor,
                       String skinColor, String eyeColor, String birthYear, String gender);

        void finishActivity();

        void showGenericError();

        void setupView();

        void setFilmsInAdapter(List<String> films);
    }

    interface Presenter {
        void onViewCreated(String url);
        void setView(DetailsCharacterContract.View view);
    }

}
