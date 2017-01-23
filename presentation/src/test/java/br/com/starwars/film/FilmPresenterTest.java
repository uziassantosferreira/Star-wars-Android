package br.com.starwars.film;

import com.google.android.gms.vision.barcode.Barcode;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.models.Film;
import br.com.starwars.domain.models.Movie;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.film.di.FilmComponent;
import br.com.starwars.listcharacters.ListCharactersContract;
import br.com.starwars.listcharacters.ListCharactersPresenter;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Uzias on 22/01/17.
 */

public class FilmPresenterTest {
    @Mock
    FilmContract.View view;

    @Mock
    CharactersUseCase charactersUseCase;

    @Mock
    SchedulerProvider threds;

    FilmContract.Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new FilmPresenter(charactersUseCase, threds);
        when(threds.mainThread()).thenReturn(Schedulers.trampoline());
        when(threds.io()).thenReturn(Schedulers.trampoline());


        presenter.setView(view);
        when(charactersUseCase.getMovieByName(null)).thenReturn(new Single<Movie>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Movie> observer) {
                Movie movie = new Movie();
                movie.setPosterPath("");
                observer.onSuccess(movie);
            }
        });

        when(charactersUseCase.getFilmByUrl(null)).thenReturn(new Single<Film>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Film> observer) {
                observer.onSuccess(new Film());
            }
        });

    }


    @Test
    public void testGetFilm() {
        presenter.onViewCreated(null);
        verify(view, times(1)).setTitle(null);
    }

    @Test
    public void testGetMovie() {
        presenter.onViewCreated(null);
        verify(view, times(1)).setImage("");
    }

    @Test
    public void testPreconditions() {
        assertNotNull("UseCase is null", charactersUseCase);
        assertNotNull("View is null", view);
        assertNotNull("SchedulerProvider is null", threds);
    }


}
