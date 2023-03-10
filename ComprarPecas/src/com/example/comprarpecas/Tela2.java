package com.example.comprarpecas;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Tela2 extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ArrayList<String> Lista = new ArrayList<String>();
        
        Lista.add("HD 250 GB - R$50");
        Lista.add("HD 500 GB - R$120");
        Lista.add("HD 1 TB - R$200");
        
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.lista1,Lista));
        
        ListView lv = getListView();
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent anterior = getIntent();
				String memoria = anterior.getStringExtra("memoria_selecionada");
				String memoria_valor = anterior.getStringExtra("memoria_valor");
				TextView selecionado = (TextView)view;
				
				double valor = 0;
				
				if(position==0) valor = 50;
        		else if(position==1) valor = 120;
        		else valor = 200;
				
				Intent navegar_tela3 = new Intent(Tela2.this,Tela3.class);
				
				navegar_tela3.putExtra("hd_selecionado", selecionado.getText().toString());
				navegar_tela3.putExtra("hd_valor", String.valueOf(valor));
				navegar_tela3.putExtra("memoria_selecionada", memoria);
				navegar_tela3.putExtra("memoria_valor", memoria_valor);
				startActivity(navegar_tela3);
			}});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela2, menu);
        return true;
    }
}
