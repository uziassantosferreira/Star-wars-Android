package br.com.starwars.listcharacters.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import br.com.starwars.di.PerActivity;
import br.com.starwars.listcharacters.ListCharactersAdapter;
import br.com.starwars.listcharacters.ListCharactersContract;
import br.com.starwars.listcharacters.ListCharactersPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Uzias on 17/01/17.
 */

@Module
public class ListCharactersModule {

    @PerActivity
    @Provides
    ListCharactersAdapter provideListCharactersAdapter() {
        return new ListCharactersAdapter();
    }

    @PerActivity
    @Provides
    ListCharactersContract.Presenter providePresenter() {
        return new ListCharactersPresenter();
    }

    @PerActivity
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

}
