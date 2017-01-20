package br.com.starwars.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.StartWarsApplication;
import br.com.starwars.di.AppComponent;
import br.com.starwars.navigation.Navigator;

/**
 * Created by Uzias on 17/01/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Inject
    protected Navigator navigator;

    public AppComponent getAppComponent() {
        return StartWarsApplication.getInstance().getApplicationComponent();
    }

    public void showProgressDialog() {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.global_loading));
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!progressDialog.isShowing() && !isFinishing()) {
            progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            if (!isFinishing()) {
                progressDialog.dismiss();
            }
        }
    }
}
