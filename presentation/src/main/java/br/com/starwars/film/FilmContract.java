package br.com.starwars.film;


/**
 * Created by Uzias on 20/01/17.
 */

public interface FilmContract {

    interface View {
        String getUrlInIntent();
        void setTitle(String title);
        void setImage(String path);
    }

    interface Presenter {
        void onViewCreated();
        void setView(View view);
    }
}
