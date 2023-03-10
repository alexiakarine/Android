package com.exmple.troco;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText valor_produto,dinheiro;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valor_produto = (EditText)findViewById(R.id.editText1);
        dinheiro = (EditText)findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void calcular(View v){
    	//Verifica se tem alguma coisa na caixa de texto
    	if(valor_produto.getText().toString().length()==0)
    	{
    		//Caso não tenha mostrar mensagem
    		Toast.makeText(getApplicationContext(), "Coloque um valor para o produto!", 2000).show();
    		//Para o código aqui
    		return;
    	}
    	if(dinheiro.getText().toString().length()==0)
    	{
    		Toast.makeText(getApplicationContext(), "Coloque um valor para o dinheiro!", 2000).show();
    		return;
    	}
    	double vProduto, vDinheiro, total;
    	vProduto = Double.parseDouble(valor_produto.getText().toString());
    	vDinheiro = Double.parseDouble(dinheiro.getText().toString());
    	
    	//Verifica se o valor do dinheiro é menor que o valor do produto
    	if(vProduto>vDinheiro)
    	{
    		//Caso sim mostre a mensagem
    		Toast.makeText(getApplicationContext(), "Coloque um valor para o dinheiro que seja maior ou igual o valor do produto!", 2000).show();
    		return;
    	}
    	
    	total = vDinheiro-vProduto;
    	
    	Integer nota100 = (int) Math.floor(total/100);
    	total-= 100*nota100;
    	
    	Integer nota50 = (int) Math.floor(total/50);
    	total-= 50*nota50;
    	
    	Integer nota20 = (int) Math.floor(total/20);
    	total-= 20*nota20;
    	
    	Integer nota10 = (int) Math.floor(total/10);
    	total-= 10*nota10;
    	
    	Integer nota5 = (int) Math.floor(total/5);
    	total-= 5*nota5;
    	
    	Integer nota2 = (int) Math.floor(total/2);
    	total-= 2*nota2;
    	
    	Integer nota1 = (int) Math.floor(total/1);
    	total-= 1*nota1;
    	//Cria um intent declarando que a fonte(da onde ele veio) desse intent
    	//é ele mesmo e para onde ele vai(ActivityDaLista)
    	Intent intent = new Intent(MainActivity.this, ActivityDaLista.class);
    	//Passa um valor(ou vários) para a outra activity 
    	intent.putExtra("nota100", nota100.toString());
    	intent.putExtra("nota50", nota50.toString());
    	intent.putExtra("nota20", nota20.toString());
    	intent.putExtra("nota10", nota10.toString());
    	intent.putExtra("nota5", nota5.toString());
    	intent.putExtra("nota2", nota2.toString());
    	intent.putExtra("nota1", nota1.toString());
    	//Inicia a outra tela
    	//startActivity(intent);
    	//Inicia uma tela com retorno
    	startActivityForResult(intent/*intent*/, 1/*requisição*/);
    }
    
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
    	if(resultCode==1)
    	{
    		//..()
    	}
    }
}
