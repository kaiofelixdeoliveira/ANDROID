package br.com.eniac.DeliveryExpress;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class LocalizarEntregador extends FragmentActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_localizarentregador);

        GoogleMapOptions options = new GoogleMapOptions();
        options.zOrderOnTop(true);
        SupportMapFragment mapFrag = SupportMapFragment.newInstance(options);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.llContainer, mapFrag);
        ft.commit();
    }

}
