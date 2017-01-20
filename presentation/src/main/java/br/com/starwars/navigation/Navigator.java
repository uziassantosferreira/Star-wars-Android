package br.com.starwars.navigation;



import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starwars.detailscharacter.DetailsCharacterActivity;

/**
 * Created by Uzias on 18/01/17.
 */

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void goToDetailsCharacter(Context context, String url){
        DetailsCharacterActivity.startActivity(context, url);
    }

}