package br.com.starwars.film;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.models.Film;
import br.com.starwars.domain.models.Movie;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.utils.BitmapUtils;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 20/01/17.
 */

public class FilmPresenter implements FilmContract.Presenter {

    private CharactersUseCase charactersUseCase;
    private SchedulerProvider schedulerProvider;
    private FilmContract.View view;
    private Movie movie;

    public FilmPresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        this.charactersUseCase = charactersUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void onViewCreated(String url) {
        getFilm(url);

    }

    private void getFilm(String url) {
        charactersUseCase.getFilmByUrl(url)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Film>() {
                    @Override
                    public void onSuccess(Film film) {
                        view.setTitle(film.getTitle());
                        getMovie(film);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private void getMovie(Film film){
        charactersUseCase.getMovieByName(film.getTitle())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(Movie movie) {
                        FilmPresenter.this.movie = movie;
                        if (movie.isSavedBitmapLocal()){
                            view.setImage(new File(movie.getPosterPath()));
                        }else{
                            view.setImage(movie.getPosterPath());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void setView(FilmContract.View view) {
        this.view = view;
    }

    @Override
    public void picassoLoadedPath() {
        if (!movie.isSavedBitmapLocal()){
            Bitmap bitmap = view.getBitmapFilm();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            charactersUseCase.saveImageInBase64(byteArray)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe(new DisposableSingleObserver<File>() {
                        @Override
                        public void onSuccess(File value) {
                            movie.setPosterPath(value.getPath());
                            movie.setSavedBitmapLocal(true);
                            saveMovie();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    private void saveMovie() {
        charactersUseCase.saveMovie(movie)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(Movie value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}
