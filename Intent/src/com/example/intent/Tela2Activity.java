package com.example.intent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Tela2Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tela2, menu);
        return true;
    }
    
    public void Voltar(View v){
    	//Volta para a activity anterior
    	//As activities são organizadas sob
    	//a forma de pilha, a última é a primeira a
    	//sair da memória
    	finish();
    }
    public void Telas(View v){
    	Intent navegar = new Intent(Tela2Activity.this,Tela2Activity.class);//Esta linha indica transição
    	startActivity(navegar);//Realiza a Transição
    }
}
