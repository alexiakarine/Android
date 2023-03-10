package com.example.calcfrequencia;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText txtAulas,txtFaltas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    	txtAulas = (EditText)findViewById(R.id.editText4);
        txtFaltas = (EditText)findViewById(R.id.editText3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void mostrarInformacoes(View v){
    	Double aulas,faltas,frequencia;
    	
    	aulas = Double.parseDouble(txtAulas.getText().toString());
    	faltas = Double.parseDouble(txtFaltas.getText().toString());
    	frequencia = ((aulas-faltas)*100)/aulas;
    	
    	if(frequencia>=75)
    	{
    		Toast.makeText(getApplicationContext(), "Frequência normal\nFrequência: "+frequencia, 2000).show();
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(), "Frequência abaixa\nFrequência: "+frequencia, 2000).show();
    	}
    }
}
