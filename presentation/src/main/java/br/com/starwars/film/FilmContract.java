package br.com.starwars.film;


import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by Uzias on 20/01/17.
 */

public interface FilmContract {

    interface View {
        void setTitle(String title);
        void setImage(String path);
        Bitmap getBitmapFilm();

        void setImage(File file);
    }

    interface Presenter {
        void onViewCreated(String url);
        void setView(View view);


        void picassoLoadedPath();
    }
}
