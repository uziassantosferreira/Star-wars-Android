package br.com.starwars.detailscharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

import br.com.starwars.film.FilmFragment;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> films;

    public DetailsCharacterFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(List<String> films){
        this.films = films;
    }

    @Override
    public Fragment getItem(int position) {
        return FilmFragment.newFragment(films.get(position));
    }

    @Override
    public int getCount() {
        return films != null ? films.size() : 0;
    }
}
