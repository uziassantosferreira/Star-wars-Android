package br.com.starwars.detailscharacter;

import com.google.android.gms.vision.barcode.Barcode;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
import br.com.starwars.listcharacters.ListCharactersPresenter;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Uzias on 22/01/17.
 */

public class DetailsCharacterTest {

    @Mock
    DetailsCharacterContract.View view;

    @Mock
    CharactersUseCase charactersUseCase;

    @Mock
    SchedulerProvider threds;

    DetailsCharacterContract.Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailsCharacterPresenter(charactersUseCase, threds);
        when(threds.mainThread()).thenReturn(Schedulers.trampoline());
        when(threds.io()).thenReturn(Schedulers.trampoline());
        when(charactersUseCase.getCharacterByUrl("")).thenReturn(new Single<Character>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Character> observer) {
                observer.onSuccess(new Character());
            }
        });
        presenter.setView(view);
    }

    @Test
    public void testOnViewCreated() {
        presenter.onViewCreated(null);
        verify(view, times(1)).setupView();
    }

    @Test
    public void testUrlNull(){
        presenter.onViewCreated(null);
        verify(view, times(1)).finishActivity();
    }

    @Test
    public void testGetListCharacter(){
        presenter.onViewCreated("");

        verify(view, times(1)).setFilmsInAdapter(null);
        verify(view, times(1)).showProgressDialog();
        verify(view, times(1)).hideProgressDialog();
    }


    @Test
    public void testPreconditions() {
        assertNotNull("UseCase is null", charactersUseCase);
        assertNotNull("View is null", view);
        assertNotNull("SchedulerProvider is null", threds);
    }


}
