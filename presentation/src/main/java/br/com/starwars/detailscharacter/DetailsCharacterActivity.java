package br.com.starwars.detailscharacter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.base.BaseActivity;
import br.com.starwars.detailscharacter.di.DetailsCharacterModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Uzias on 20/01/17.
 */

public class DetailsCharacterActivity extends BaseActivity implements DetailsCharacterContract.View {

    private static final String BUNDLE_EXTRAS_URL = "bundle_extras_url";

    @Inject
    DetailsCharacterContract.Presenter presenter;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_character);

        ButterKnife.bind(this);

        initializeInjector();
        presenter.setView(this);
        presenter.onViewCreated();

    }

    private void initializeInjector() {
        getAppComponent().plus(new DetailsCharacterModule()).inject(this);
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
    public String getUrlInIntent() {
        return getIntent() != null ? getIntent().getStringExtra(BUNDLE_EXTRAS_URL) : null;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public static void startActivity(Context context, String url){
        Intent intent = new Intent(context, DetailsCharacterActivity.class);
        intent.putExtra(BUNDLE_EXTRAS_URL, url);
        context.startActivity(intent);
    }
}
