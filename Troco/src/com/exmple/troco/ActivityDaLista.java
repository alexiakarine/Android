package com.exmple.troco;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityDaLista extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pega o intent enviado
        Intent intent = getIntent();
        //Cria uma lista de string(texto)
        ArrayList<String> lista = new ArrayList<String>();
        //Declara as váriaveis
        Integer nota100=0,nota50=0,nota20=0,nota10=0,nota5=0,nota2=0,nota1=0,total=0;
        //Pega os valores enviados e coloca nas váriaveis
        nota100 = Integer.parseInt(intent.getStringExtra("nota100"));
        nota50 = Integer.parseInt(intent.getStringExtra("nota50"));
        nota20 = Integer.parseInt(intent.getStringExtra("nota20"));
        nota10 = Integer.parseInt(intent.getStringExtra("nota10"));
        nota5 = Integer.parseInt(intent.getStringExtra("nota5"));
        nota2 = Integer.parseInt(intent.getStringExtra("nota2"));
        nota1 = Integer.parseInt(intent.getStringExtra("nota1"));
        
        total=(100*nota100)+(50*nota50)+(20*nota20)+(10*nota10)+(5*nota5)+(2*nota2)+(1*nota1);
        //Adiciona os valores na lista
        if(nota100>0) lista.add(String.format("%dx R$100",nota100));
        if(nota50>0) lista.add(String.format("%dx R$50",nota50));
        if(nota20>0) lista.add(String.format("%dx R$20",nota20));
        if(nota10>0) lista.add(String.format("%dx R$10",nota10));
        if(nota5>0) lista.add(String.format("%dx R$5",nota5));
        if(nota2>0) lista.add(String.format("%dx R$2",nota2));
        if(nota1>0) lista.add(String.format("%dx R$1",nota1));
        if(total>0) lista.add("----------------------------");
        
        lista.add(String.format("Troco total: R$%d",total));
        //Cria um adaptador para poder mostrar a lista
        this.setListAdapter(//Coloca um adaptador de lista
        		new ArrayAdapter<String>//Define que tipo de adaptador que é
        			(this, R.layout.lista_notas, lista)//Fala que essa tela, vai receber a aparencia de
        			//R.layout.lista_notas(xml layout), e os valores nele serão da lista
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_activity_da_lista, menu);
        return true;
    }
}
