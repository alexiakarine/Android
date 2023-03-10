package com.example.exercicioidade;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText txtAno,txtNascimento;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtAno = (EditText)findViewById(R.id.txtAno);
        txtNascimento = (EditText)findViewById(R.id.txtNascimento); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Verificar(View v){
    	Integer ano,nascimento,idade;
    	ano = Integer.parseInt(txtAno.getText().toString());
    	nascimento = Integer.parseInt(txtNascimento.getText().toString());
    	idade = ano-nascimento;
    	
    	if(idade<0) Toast.makeText(getApplicationContext(), "Idade inválida!\nColoque um ano maior ou igual a "+ano, 2000).show();
    	else Toast.makeText(getApplicationContext(), "Idade: "+idade, 2000).show();
    }
}
