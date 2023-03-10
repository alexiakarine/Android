package com.example.phpjsonlistviewmysql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView lv;// gerencia listview do layout	
	ArrayList<String> info = new ArrayList<String>();// armazena inf. lidas do BD
	private ArrayAdapter<String> adapter;// adaptador para listview
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // associa listview com adaptador e array de inf.
	    lv = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, info);
        lv.setAdapter(adapter);
        // realiza consulta ao BD em background
        ConsultaBackGround con = new ConsultaBackGround();
        con.execute("");// executa tarefa
       
        /*Intent intent = new Intent(MainActivity.this,Tela2.class);
        
        int index = 0;
        int rows = 4;
        int caixa = index*rows;
        for(int i=0;i<rows;i++)
        {
        	String informacao = info.get(caixa+i);
        	intent.putExtra("nome", informacao);
        }
        
        intent.putExtra("array", info);
        
        startActivity(intent);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    // classe para execução em background da consulta ao BD
    public class ConsultaBackGround extends AsyncTask<String,Integer,Integer>{
    	@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			Toast.makeText(getApplicationContext(), "Registro:"+values[0], 100).show();
			// atualiza adpter do listview
			adapter.notifyDataSetChanged();
		}

		@Override
    	  protected void onPreExecute(){
    	    //incia tarefa
    		Toast.makeText(getApplicationContext(), "Iniciando Tarefa", 100).show();
    	  }
    	
		@Override
		protected Integer doInBackground(String... arg0) {
			try {
				consultar();// tarefa a se executada em background
			} catch (UnsupportedEncodingException e) {				
				Toast.makeText(getApplicationContext(), "Erro método consultar:"+e.getMessage(), 2000).show();
			}
			return null;
		}
		
		 @Override
		  protected void onPostExecute(Integer numero){
			 // avisa que tarefa foi finalizada
		    Toast.makeText(getApplicationContext(), "Finalizando Tarefa", 100).show();	        
		  }
		// realiza consulta chamado web service em php com acesso a b.d. mysql
		public void consultar() throws UnsupportedEncodingException{
	        // repassa informações das variáveis para posterior cadastro
	        String acao = "listar"; 
	        // Cria String informações que irá conter os dados a serem enviados no padrão de protocolo HTTP              
	        String informacoes = URLEncoder.encode("acao", "UTF-8") 
	                       + "=" + URLEncoder.encode(acao, "UTF-8"); 
	        String resposta = "";
	          // criar buffer de memória para enviar e receber resutlado do HTTP
	        BufferedReader respostaServidor=null;

	        // Envia a informação (dados) 
	        try {          	
	            // Defined URL  que receberá os dados
	            URL url = new URL("http://10.0.2.2/listar.php");            
	            // Send POST data request (envia)
	            URLConnection conexao = url.openConnection(); 
	            conexao.setDoOutput(true); 
	            OutputStreamWriter bufferDados = new OutputStreamWriter(conexao.getOutputStream()); 
	            bufferDados.write( informacoes ); // grava o buffer com as informações
	            bufferDados.flush(); // limpar o buffer
	            // Get the server response (obtém reposta do serividor htttp)            
	            respostaServidor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
	            StringBuilder respostaRecebida = new StringBuilder();
	            String linhaRecebidaResposta = null;
	            int registro=0;
	            // Read Server Response
	            while((linhaRecebidaResposta = respostaServidor.readLine()) != null)
	            {	            	
	    			JSONObject response;// objeto json
	    			try
	    			{   // converte leitura da inf. em json
	    				response = new JSONObject(linhaRecebidaResposta);
	    				// define rotulo de início de leitura
	    				JSONArray main = response.optJSONArray("dados");
	    				// ler a inf. associada ao rotulo de leitura
	    				for(int i = 0; i < main.length(); i++)
	    				{   // obtém uma associação do rotulo
	    					JSONObject child = main.getJSONObject(i);	    					
	    					resposta += "nome : "+child.optString("nome")+"\nfunção : "+child.optString("funcao")+"\n";
	    					registro++;// incrementa contador de registro
	    					publishProgress(registro);// atualiza UI
	    					//adiconar ao array para mostrar em listView
	    					info.add("Nome: "+child.optString("nome"));
	    					info.add("RG: "+child.optString("rg"));
	    					info.add("CPF: "+child.optString("cpf"));
	    					info.add("");
	    				}
	    			}
	    			catch(JSONException e)
	    			{
	    				resposta = e.getMessage();
	    				Toast.makeText(getApplicationContext(), "Erro chamada ao Web Service:"+e.getMessage(), 2000).show();
	    			}
	            }
	            
	        }
	        catch(Exception ex)
	        {
	        	Toast.makeText(getApplicationContext(), "Erro de conexãor:"+ex.getMessage(), 2000).show();
	        }
	        finally
	        {
	            try
	            {	 
	                respostaServidor.close();
	            }

	            catch(Exception ex) {
	            	Toast.makeText(getApplicationContext(), "Erro fechar conexão:"+ex.getMessage(), 2000).show();
	            }
	        }
	         
	    	
	    }
    	
    }
    
}
