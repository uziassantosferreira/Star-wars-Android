package br.com.starwars.film;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.base.BaseFragment;
import br.com.starwars.film.di.FilmModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Uzias on 20/01/17.
 */

public class FilmFragment extends BaseFragment implements FilmContract.View {

    private static final java.lang.String BUNDLE_EXTRAS_URL = "bundle_extras_url";

    @BindView(R.id.textview_title)
    TextView textViewTitle;

    @BindView(R.id.imageview)
    ImageView imageView;

    @Inject
    FilmContract.Presenter presenter;

    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.bind(this, view);

        initializeInjector();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.onViewCreated();
    }

    @Override
    public String getUrlInIntent() {
        return getArguments() != null ? getArguments().getString(BUNDLE_EXTRAS_URL) : null;
    }

    @Override
    public void setTitle(String title) {
        textViewTitle.setText(title);
    }

    @Override
    public void setImage(String path) {
        Picasso.with(getContext()).load(path).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                FilmFragment.this.bitmap = bitmap;
                imageView.setImageBitmap(bitmap);
                presenter.picassoLoadedPath();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    @Override
    public Bitmap getBitmapFilm() {
        return bitmap;
    }

    @Override
    public void setImage(File file) {
        Picasso.with(getContext()).load(file).into(imageView);
    }

    private void initializeInjector() {
        getAppComponent().plus(new FilmModule()).inject(this);
    }

    public static Fragment newFragment(String url) {
        FilmFragment filmFragment = new FilmFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_EXTRAS_URL, url);
        filmFragment.setArguments(bundle);
        return filmFragment;
    }
}
