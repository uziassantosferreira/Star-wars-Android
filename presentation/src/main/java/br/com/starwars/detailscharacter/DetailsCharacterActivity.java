package br.com.starwars.detailscharacter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.starwars.R;
import br.com.starwars.base.BaseActivity;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_character);
    }
}
