package br.com.starwars.listcharacters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;

import java.util.List;

import javax.inject.Inject;

import br.com.starwars.R;
import br.com.starwars.base.BaseActivity;
import br.com.starwars.domain.models.Character;
import br.com.starwars.listcharacters.di.ListCharactersModule;
import br.com.starwars.utils.ListenerOnClickItemPosition;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.starwars.listcharacters.ListCharactersContract.*;

/**
 * Created by Uzias on 17/01/17.
 */

public class ListCharactersActivity extends BaseActivity implements View, ListenerOnClickItemPosition {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Inject
    Presenter presenter;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.qrcode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.qrcode){
            onClickQRCode();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupView() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listCharactersAdapter);
        listCharactersAdapter.setListenerOnClickItemPosition(this);
    }

    @Override
    public void setListAndNotifyAdaper(List<Character> list) {
        listCharactersAdapter.setList(list);
        listCharactersAdapter.notifyDataSetChanged();
    }

    @Override
    public void openScanQRCode() {
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withCenterTracker()
                .withText(getString(R.string.activity_list_characters_scan_qrcode))
                .withResultListener(presenter::barcodeScanned)
                .build();
        materialBarcodeScanner.startScan();
    }

    @Override
    public void goToDetailsCharacter(String url) {
        navigator.goToDetailsCharacter(this, url);
    }

    @Override
    public void showGenericError() {
        Toast.makeText(this, R.string.global_generic_error, Toast.LENGTH_LONG);
    }

    @Override
    public void onClickItemPosition(int position) {
        presenter.clickedItemPosition(position);
    }

    private void onClickQRCode(){
        presenter.clickedMenuItemQRCode();
    }

    private void initializeInjector() {
        getAppComponent().plus(new ListCharactersModule()).inject(this);
    }
}
