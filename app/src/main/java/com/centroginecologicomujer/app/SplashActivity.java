package com.centroginecologicomujer.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Handler miHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        miHandler = new Handler();
        miHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent miIntent = new Intent(SplashActivity.this, MenuPrincipalActivity.class);
                startActivity(miIntent);
                finish();

            }
        }, 1000 * 3);

    }

}
