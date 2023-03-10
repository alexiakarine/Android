package com.example.intent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void SegundaTela(View v){
    	//Cria um objeto do tipo Intent
    	Intent navegar = new Intent(MainActivity.this,Tela2Activity.class);//Esta linha indica transição
    	startActivity(navegar);//Realiza a Transição
    }
}
