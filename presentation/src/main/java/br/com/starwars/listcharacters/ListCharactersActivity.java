package br.com.starwars.listcharacters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.base.BaseActivity;
import br.com.starwars.domain.models.Character;
import br.com.starwars.listcharacters.di.ListCharactersModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersActivity extends BaseActivity implements ListCharactersContract.View{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Inject
    ListCharactersContract.Presenter presenter;

    @Inject
    ListCharactersAdapter listCharactersAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_characters);

        initializeInjector();
        presenter.setView(this);
        presenter.onViewCreated();

    }

    @Override
    public void setupView() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listCharactersAdapter);
    }

    @Override
    public void setListAndNotifyAdaper(List<Character> list) {
        listCharactersAdapter.setList(list);
        listCharactersAdapter.notifyDataSetChanged();
    }

    private void initializeInjector() {
        getAppComponent().plus(new ListCharactersModule()).inject(this);
    }

}
