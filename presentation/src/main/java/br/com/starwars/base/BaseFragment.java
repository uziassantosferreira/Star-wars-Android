package br.com.starwars.base;

import android.support.v4.app.Fragment;

import br.com.starwars.StartWarsApplication;
import br.com.starwars.di.AppComponent;

/**
 * Created by Uzias on 20/01/17.
 */

public class BaseFragment extends Fragment {

    public AppComponent getAppComponent() {
        return StartWarsApplication.getInstance().getApplicationComponent();
    }

    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }
}
