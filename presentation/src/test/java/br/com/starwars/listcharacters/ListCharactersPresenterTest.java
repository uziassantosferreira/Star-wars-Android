package br.com.starwars.listcharacters;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.starwars.domain.interactor.CharactersUseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.providers.SchedulerProvider;
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

public class ListCharactersPresenterTest {

    @Mock
    ListCharactersContract.View view;

    @Mock
    CharactersUseCase charactersUseCase;

    @Mock
    SchedulerProvider threds;

    ListCharactersContract.Presenter presenter;

    ArrayList<Character> characters;

    Barcode barcode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ListCharactersPresenter(charactersUseCase, threds);
        when(threds.mainThread()).thenReturn(Schedulers.trampoline());
        when(threds.io()).thenReturn(Schedulers.trampoline());

        characters = new ArrayList<>();
        characters.add(new Character());
        presenter.setView(view);
        barcode = new Barcode();
        when(charactersUseCase.getListCharacters()).thenReturn(new Single<List<Character>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super List<Character>> observer) {
                observer.onSuccess(characters);
            }
        });

        when(charactersUseCase.getCharacterByUrl(barcode.displayValue)).thenReturn(new Single<Character>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Character> observer) {
                observer.onSuccess(new Character());
            }
        });
    }


    @Test
    public void testClickedMenuItemQRCode() {
        presenter.clickedMenuItemQRCode();
        verify(view, times(1)).openScanQRCode();
    }

    @Test
    public void testBarcodeScanned() {
        presenter.barcodeScanned(barcode);
        verify(view, times(1)).showProgressDialog();
        verify(view, times(1)).hideProgressDialog();
    }

    @Test
    public void testOnViewCreated() {
        presenter.onViewCreated();
        verify(view, times(1)).setupView();
        verify(view, times(1)).setListAndNotifyAdaper(characters);
    }

    @Test
    public void testClickedItemPosition() {
        presenter.onViewCreated();
        presenter.clickedItemPosition(0);
        verify(view, times(1)).goToDetailsCharacter(null);
    }


    @Test
    public void testPreconditions() {
        assertNotNull("UseCase is null", charactersUseCase);
        assertNotNull("View is null", view);
        assertNotNull("SchedulerProvider is null", threds);
    }


}
