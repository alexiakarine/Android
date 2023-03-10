package com.example.comprarpecas;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
        if(intent!=null)
        {
        	String valor = intent.getStringExtra("valor");
        	Toast.makeText(getApplicationContext(), "Valor passado: "+valor, 2000).show();
        }
        
        // Criando a lista
        ArrayList<String> Lista = new ArrayList<String>();
        
        // Colocando as informações na lista
        Lista.add("Memória RAM 4 GB - R$ 52");
        Lista.add("Memória RAM 8 GB - R$ 100");
        Lista.add("Memória RAM 16 GB - R$ 160");
        
        // Mostra na tela a lista 
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.lista1,Lista));
        
        // Ponte liga o click com a função abaixo.
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener(){ 
        	
        	// Função de interação, ou seja, o que vai fazer quando clicar
        	public void onItemClick(AdapterView<?> parent, View view, int position,
        		long id) {
        		TextView selecionado = (TextView)view;
        		double valor = 0;
        		//Pega o texto do componente da lista
        		//selecionado.getText().toString();
        		
        		if(position==0) valor = 52;
        		else if(position==1) valor = 100;
        		else valor = 160;
        		
        		//Cria uma nova janela
        		Intent navegar_tela2 = new Intent(MainActivity.this,Tela2.class);
        		
        		//Indo para tela 2
        		navegar_tela2.putExtra("memoria_selecionada", selecionado.getText().toString());
        		navegar_tela2.putExtra("memoria_valor", String.valueOf(valor));
        		
        		startActivity(navegar_tela2);
        }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
