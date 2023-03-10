package com.example.phpmysqlandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Tela2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela2, menu);
        return true;
    }
}
