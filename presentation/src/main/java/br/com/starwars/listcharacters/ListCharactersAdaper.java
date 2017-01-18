package br.com.starwars.listcharacters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.starwars.R;
import br.com.starwars.model.Character;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersAdaper  extends RecyclerView.Adapter<ListCharactersAdaper.ViewHolder>{

    private final List<Character> characters;

    public ListCharactersAdaper(List<Character> characters){
        this.characters = characters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.setFields(character.getName(), character.getUrl());
    }

    @Override
    public int getItemCount() {
        return characters == null ? 0  : characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_title)
        TextView textViewTitle;

        @BindView(R.id.textview_sub_title)
        TextView textViewSubTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setFields(String title, String subTitle) {
            textViewTitle.setText(title);
            textViewSubTitle.setText(subTitle);
        }
    }

}
