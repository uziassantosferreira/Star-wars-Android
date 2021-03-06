package br.com.starwars.detailscharacter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.base.BaseActivity;
import br.com.starwars.detailscharacter.di.DetailsCharacterModule;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterActivity extends BaseActivity implements DetailsCharacterContract.View {

    private static final String BUNDLE_EXTRAS_URL = "bundle_extras_url";

    @BindView(R.id.textview_url)
    TextView textViewUrl;

    @BindView(R.id.textview_height)
    TextView textViewHeight;

    @BindView(R.id.textview_mass)
    TextView textViewMass;

    @BindView(R.id.textview_hair_color)
    TextView textViewHairColor;

    @BindView(R.id.textview_skin_color)
    TextView textViewSkinColor;

    @BindView(R.id.textview_eye_color)
    TextView textViewEyeColor;

    @BindView(R.id.textview_birth_year)
    TextView textViewBirthYear;

    @BindView(R.id.textview_gender)
    TextView textViewGender;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;

    @Inject
    DetailsCharacterContract.Presenter presenter;

    @Inject
    DetailsCharacterFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_character);

        ButterKnife.bind(this);

        initializeInjector();
        presenter.setView(this);
        presenter.onViewCreated(getUrlInIntent());

    }

    @Override
    public void setFields(String name, String url, String height, String mass, String hairColor,
                          String skinColor, String eyeColor, String birthYear, String gender) {
        getSupportActionBar().setTitle(name);
        textViewUrl.setText(url);
        textViewHeight.setText(height);
        textViewMass.setText(mass);
        textViewHairColor.setText(hairColor);
        textViewSkinColor.setText(skinColor);
        textViewEyeColor.setText(eyeColor);
        textViewBirthYear.setText(birthYear);
        textViewGender.setText(gender);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showGenericError() {
        Toast.makeText(this, R.string.global_generic_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupView() {
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

    }

    @Override
    public void setFilmsInAdapter(List<String> films) {
        fragmentPagerAdapter.setList(films);
        fragmentPagerAdapter.notifyDataSetChanged();
        circleIndicator.setViewPager(viewPager);
    }

    private String getUrlInIntent() {
        return getIntent() != null ? getIntent().getStringExtra(BUNDLE_EXTRAS_URL) : null;
    }

    private void initializeInjector() {
        getAppComponent().plus(new DetailsCharacterModule(getSupportFragmentManager())).inject(this);
    }

    public static void startActivity(Context context, String url){
        Intent intent = new Intent(context, DetailsCharacterActivity.class);
        intent.putExtra(BUNDLE_EXTRAS_URL, url);
        context.startActivity(intent);
    }
}
