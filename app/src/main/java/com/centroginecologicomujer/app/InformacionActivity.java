package com.centroginecologicomujer.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class InformacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://centroginecologicomujer.com");


    }
}
