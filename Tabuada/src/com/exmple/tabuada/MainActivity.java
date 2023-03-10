package com.exmple.tabuada;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText txtNumero;
	TextView tvResultado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNumero = (EditText)findViewById(R.id.editText1);
        tvResultado = (TextView)findViewById(R.id.textView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Verificar(View v){
    	int numero = 0;
    	numero = Integer.parseInt(txtNumero.getText().toString());
    	String texto = "";
    	for(int i=1;i<=10;i++){
    		texto+=String.format("%d * %d = %d\n", numero,i,(numero*i));
    	}
    	tvResultado.setText(texto);
    }
}
