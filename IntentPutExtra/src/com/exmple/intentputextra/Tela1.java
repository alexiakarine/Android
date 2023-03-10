package com.exmple.intentputextra;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Tela1 extends Activity {
	EditText mensagem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        mensagem = (EditText)findViewById(R.id.editText1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela1, menu);
        return true;
    }
    
    public void irTela2(View v){
    	Intent navegar = new Intent(Tela1.this,Tela2.class);
    	navegar.putExtra("msg", mensagem.getText().toString());
    	startActivity(navegar);
    }
}
