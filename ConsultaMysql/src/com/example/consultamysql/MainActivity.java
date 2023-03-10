package com.example.consultamysql;

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
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView lv;//Componente listview adicionado diretamente no layout
	ArrayList<String> info = new ArrayList<String>();//Armazena os resultados do banco de dados
	ArrayAdapter<String> adapter; //Adaptador para o listview

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listView1);

		adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_spinner_item, info);
		lv.setAdapter(adapter);
		//Realiza a consulta no banco de dados em segundo plano(background)
		ConsultaBackGround con = new ConsultaBackGround();
		con.execute("");//Executa a tarefa
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	// classe para execu��o em background da consulta ao BD
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
				Toast.makeText(getApplicationContext(), "Erro m�todo consultar:"+e.getMessage(), 2000).show();
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
	        // repassa informa��es das vari�veis para posterior cadastro
	        String acao = "listar"; 
	        // Cria String informa��es que ir� conter os dados a serem enviados no padr�o de protocolo HTTP              
	        String informacoes = URLEncoder.encode("acao", "UTF-8") 
	                       + "=" + URLEncoder.encode(acao, "UTF-8"); 
	        String resposta = "";
	          // criar buffer de mem�ria para enviar e receber resutlado do HTTP
	        BufferedReader respostaServidor=null;

	        // Envia a informa��o (dados) 
	        try {          	
	            // Defined URL  que receber� os dados
	            URL url = new URL("http://10.0.2.2/listar.php");            
	            // Send POST data request (envia)
	            URLConnection conexao = url.openConnection(); 
	            conexao.setDoOutput(true); 
	            OutputStreamWriter bufferDados = new OutputStreamWriter(conexao.getOutputStream()); 
	            bufferDados.write( informacoes ); // grava o buffer com as informa��es
	            bufferDados.flush(); // limpar o buffer
	            // Get the server response (obt�m reposta do serividor htttp)            
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
	    				// define rotulo de in�cio de leitura
	    				JSONArray main = response.optJSONArray("dados");
	    				// ler a inf. associada ao rotulo de leitura
	    				for(int i = 0; i < main.length(); i++)
	    				{   // obt�m uma associa��o do rotulo
	    					JSONObject child = main.getJSONObject(i);	    					
	    					resposta += "nome : "+child.optString("nome")+"\nfun��o : "+child.optString("funcao")+"\n";
	    					registro++;// incrementa contador de registro
	    					publishProgress(registro);// atualiza UI
	    					//adiconar ao array para mostrar em listView
	    					info.add(child.optString("nome"));
	    					
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
	        	Toast.makeText(getApplicationContext(), "Erro de conex�or:"+ex.getMessage(), 2000).show();
	        }
	        finally
	        {
	            try
	            {	 
	                respostaServidor.close();
	            }

	            catch(Exception ex) {
	            	Toast.makeText(getApplicationContext(), "Erro fechar conex�o:"+ex.getMessage(), 2000).show();
	            }
	        }
	         
	    	
	    }
    	
    }
	public class ConsultaBackground2 extends AsyncTask<String, Integer, Integer> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//Inicia a tarefa
			Toast.makeText(getApplicationContext(), "Iniciando Tarefa", 100).show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			try{
				consultar();//Tarefa que ser� executada em segundo plano(background)
			}
			catch(UnsupportedEncodingException e){
				Toast.makeText(getApplicationContext(), "Erro m�todo consultar: "+e.getMessage(), 2000).show();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			Toast.makeText(getApplicationContext(), "Registro: "+values[0], 100).show();
			adapter.notifyDataSetChanged();
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			//Avisa que a tarefa foi finalizada
			Toast.makeText(getApplicationContext(), "Finalizando Tarefa", 100).show();
		}
		public void consultar() throws UnsupportedEncodingException{
	        // repassa informa��es das vari�veis para posterior cadastro
	        String acao = "listar"; 
	        // Cria String informa��es que ir� conter os dados a serem enviados no padr�o de protocolo HTTP              
	        String informacoes = URLEncoder.encode("acao", "UTF-8") 
	                       + "=" + URLEncoder.encode(acao, "UTF-8"); 
	        String resposta = "";
	          // criar buffer de mem�ria para enviar e receber resutlado do HTTP
	        BufferedReader respostaServidor=null;

	        // Envia a informa��o (dados) 
	        try {          	
	            // Defined URL  que receber� os dados
	            URL url = new URL("http://127.0.0.1/listar.php");            
	            // Send POST data request (envia)
	            URLConnection conexao = url.openConnection(); 
	            conexao.setDoOutput(true); 
	            OutputStreamWriter bufferDados = new OutputStreamWriter(conexao.getOutputStream()); 
	            bufferDados.write( informacoes ); // grava o buffer com as informa��es
	            bufferDados.flush(); // limpar o buffer
	            // Get the server response (obt�m reposta do serividor htttp)            
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
	    				// define rotulo de in�cio de leitura
	    				JSONArray main = response.optJSONArray("dados");
	    				// ler a inf. associada ao rotulo de leitura
	    				for(int i = 0; i < main.length(); i++)
	    				{   // obt�m uma associa��o do rotulo
	    					JSONObject child = main.getJSONObject(i);	    					
	    					resposta += "nome : "+child.optString("nome")+"\nfun��o : "+child.optString("funcao")+"\n";
	    					registro++;// incrementa contador de registro
	    					publishProgress(registro);// atualiza UI
	    					//adiconar ao array para mostrar em listView
	    					info.add(child.optString("nome"));
	    					
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
	        	Toast.makeText(getApplicationContext(), "Erro de conex�or:"+ex.getMessage(), 2000).show();
	        }
	        finally
	        {
	            try
	            {	 
	                respostaServidor.close();
	            }

	            catch(Exception ex) {
	            	Toast.makeText(getApplicationContext(), "Erro fechar conex�o:"+ex.getMessage(), 2000).show();
	            }
	        }
	         
	    	
	    }
		public void consultar2() throws UnsupportedEncodingException{
			//Repassa as informa��es das vari�veis para um cadastro posterior
			String acao = "listar";
			//Cria uma String informa��es que ir� conter os dados a serem enviados no padr�o de protocolo HTTP
			String informacoes = URLEncoder.encode("acao","UTF-8")
					+ "=" + URLEncoder.encode(acao,"UTF-8");
			String resposta = "";
			//Criar buffer de mem�ria para enviar e receber resultados do HTTP
			BufferedReader respostaServidor = null;
			
			//Envia as informa��es para a consulta
			try
			{
				//Define a URL que receber� os dados
				URL url = new URL("http://localhost/request.php");
				//Envia uma requisi��o do tipo POST
				URLConnection conexao = url.openConnection();
				conexao.setDoOutput(true);
				
				OutputStreamWriter bufferDados = new OutputStreamWriter(conexao.getOutputStream());
				
				bufferDados.write(informacoes);//grava no buffer as informa��es
				bufferDados.flush();//limpa o buffer
				//Pega a resposta da requisi��o do servidor HTTP
				respostaServidor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
				StringBuilder respostaRecebida = new StringBuilder();
				String linhaRecebidaResposta = null;
				int registros = 0;
				//L� os registros recebidos
				while((linhaRecebidaResposta = respostaServidor.readLine()) != null)
				{
					JSONObject response;//Objeto JSON
					try
					{
						//Converte a resposta recebida em um objeto JSON
						response = new JSONObject(linhaRecebidaResposta);
						//Pega os dados da resposta, isso pegar� todos os dados da chave "dados"
						//E faz uma lista dos objetos recebidos
						JSONArray main = response.optJSONArray("dados");
						//L� cada informa��o encontrada nessa chave
						for(int i = 0; i < main.length(); i++)
						{
							//Pega uma informa��o da chave
							JSONObject objAtual = main.getJSONObject(i);
							resposta += "nome: "+objAtual.optString("nome")+"\nfun��o: "+objAtual.optString("funcao")+"\n";
							registros++;//Incrementa o contador de registros
							publishProgress(registros);//Atualiza a UI(Interface);
							//Adiciona a lista de array para mostrar no listView
							info.add(objAtual.optString("nome"));
						}
					}
					catch(JSONException e){
						resposta = e.getMessage();
						Toast.makeText(getApplicationContext(), "Erro ao chamar o Web Service: "+resposta, 2000).show();
					}
				}
			}
			catch(Exception ex)
			{
				Toast.makeText(getApplicationContext(), "Erro de conex�o: "+ex.getMessage(), 2000).show();
			}
			finally{
				try
				{
					respostaServidor.close();
				}
				catch(Exception ex)
				{
					Toast.makeText(getApplicationContext(), "Erro ao fechar a conex�o: "+ex.getMessage(), 2000).show();
					
				}
			}
		}
	}
}
