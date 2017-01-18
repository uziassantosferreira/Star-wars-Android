package br.com.starwars.base;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import br.com.starwars.StartWarsApplication;
import br.com.starwars.di.AppComponent;
import br.com.starwars.navigation.Navigator;

/**
 * Created by Uzias on 17/01/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator navigator;

    public AppComponent getAppComponent() {
        return StartWarsApplication.getInstance().getApplicationComponent();
    }

}
