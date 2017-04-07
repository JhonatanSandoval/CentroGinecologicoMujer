package com.centroginecologicomujer.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button btnReservar = (Button) findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReservar = new Intent(MenuPrincipalActivity.this, ReserveCitaActivity.class);
                startActivity(intentReservar);
            }
        });

        Button btnInformacion = (Button) findViewById(R.id.btnInformacion);
        btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInformacion = new Intent(MenuPrincipalActivity.this, InformacionActivity.class);
                startActivity(intentInformacion);
            }
        });


    }

}
