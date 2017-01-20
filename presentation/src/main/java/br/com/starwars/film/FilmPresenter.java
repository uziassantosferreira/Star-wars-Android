package br.com.starwars.film;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.models.Film;
import br.com.starwars.domain.providers.SchedulerProvider;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Uzias on 20/01/17.
 */

public class FilmPresenter implements FilmContract.Presenter {

    private CharactersUseCase charactersUseCase;
    private SchedulerProvider schedulerProvider;
    private String url;
    private FilmContract.View view;

    public FilmPresenter(CharactersUseCase charactersUseCase, SchedulerProvider schedulerProvider) {
        this.charactersUseCase = charactersUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void onViewCreated() {
        url = view.getUrlInIntent();
        if (url != null){
            getFilm();
        }
    }

    private void getFilm() {
        charactersUseCase.getFilmByUrl(url).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(new DisposableSingleObserver<Film>() {
                    @Override
                    public void onSuccess(Film film) {
                        view.setTitle(film.getTitle());
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
}
