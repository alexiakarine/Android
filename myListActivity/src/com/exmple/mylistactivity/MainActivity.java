package com.exmple.mylistactivity;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("1 - Análise de Sistemas");
        lista.add("2 - Automação");
        lista.add("3 - Administração");
        
        this.setListAdapter(
        	new ArrayAdapter<String>
        		(this, R.layout.list_item, lista)
        );//Já existe um adaptador para isso
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position,
        			long id) {
        		// TODO Auto-generated method stub
        		String texto = ((TextView)view).getText().toString();
        		Toast.makeText(getApplicationContext(), "Selecionado: "+position+"\nTexto: "+texto, 2000).show();
        	}
		});//Observador de evento que vai gerenciar o click
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
