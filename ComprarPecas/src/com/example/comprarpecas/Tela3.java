package com.example.comprarpecas;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Tela3 extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Processador i3 - R$400");
        lista.add("Processador i5 - R$600");
        lista.add("Processador i7 - R$800");
        
        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.lista1,lista));
        
        ListView lv = getListView();
        
        
        lv.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view, int position,
        		long id) {
        		Intent anterior = getIntent();
        		String memoria = anterior.getStringExtra("memoria_selecionada");
				String memoria_valor = anterior.getStringExtra("memoria_valor");
        		String hd = anterior.getStringExtra("hd_selecionado");
        		String hd_valor = anterior.getStringExtra("hd_valor");
        		
        		TextView selecionado = (TextView)view;
        		
        		double valor = 0;
				
				if(position==0) valor = 400;
        		else if(position==1) valor = 600;
        		else valor = 800;
        		
        		String processador = selecionado.getText().toString();
        		
        		Intent navegar_resultado = new Intent(Tela3.this,Tela4.class);
        		
        		navegar_resultado.putExtra("processador_selecionado", processador);
        		navegar_resultado.putExtra("processador_valor", String.valueOf(valor));
        		navegar_resultado.putExtra("memoria_selecionada", memoria);
        		navegar_resultado.putExtra("memoria_valor", memoria_valor);
        		navegar_resultado.putExtra("hd_selecionado", hd);
        		navegar_resultado.putExtra("hd_valor", hd_valor);
        		
        		startActivity(navegar_resultado);
        }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela3, menu);
        return true;
    }
}
