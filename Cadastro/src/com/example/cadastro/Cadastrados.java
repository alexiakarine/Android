package com.example.cadastro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Cadastrados extends Activity {
	ListView lvLista;
	ArrayList<String> clientes = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrados);
        
        lvLista= (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
        lvLista.setAdapter(adapter);
        
        try {
			Consultar();
		} catch (UnsupportedEncodingException e) {
			
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastrados, menu);
        return true;
    }
    
    public void Consultar() throws UnsupportedEncodingException{
    	
    	String resposta = "";
    	BufferedReader respostaServidor = null;
    	
    	 try {
             URL url = new URL("http://10.0.2.2/consultar.php");
             URLConnection conexao = url.openConnection(); 
             conexao.setDoOutput(true); 
             
             respostaServidor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
             StringBuilder respostaRecebida = new StringBuilder();
             String linhaRecebidaResposta = null;

             while((linhaRecebidaResposta = respostaServidor.readLine()) != null)
             {
            	 clientes.add(linhaRecebidaResposta);
             }
             
             resposta = respostaRecebida.toString();
             
         }
         catch(Exception ex)
         {
              
         }
         finally
         {
             try
             {
                 respostaServidor.close();
             }

             catch(Exception ex) {}
         }
               
    }
}
