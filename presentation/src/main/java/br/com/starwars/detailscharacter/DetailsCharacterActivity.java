package br.com.starwars.detailscharacter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.base.BaseActivity;
import br.com.starwars.detailscharacter.di.DetailsCharacterModule;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterActivity extends BaseActivity implements DetailsCharacterContract.View {

    @Inject
    DetailsCharacterContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_character);

        initializeInjector();
        presenter.setView(this);
        presenter.onViewCreated();
    }

    private void initializeInjector() {
        getAppComponent().plus(new DetailsCharacterModule()).inject(this);
    }
}
