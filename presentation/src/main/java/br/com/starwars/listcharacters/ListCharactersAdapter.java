package br.com.starwars.listcharacters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class ListCharactersAdapter extends RecyclerView.Adapter<ListCharactersAdapter.ViewHolder>{

    private List<Character> characters;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.setFields(character.getName(), character.getUrl());
    }

    public void setList(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public int getItemCount() {
        return characters == null ? 0 : characters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_title)
        TextView textViewTitle;

        @BindView(R.id.textview_sub_title)
        TextView textViewSubTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void setFields(String title, String subTitle) {
            textViewTitle.setText(title);
            textViewSubTitle.setText(subTitle);
        }
    }

}
