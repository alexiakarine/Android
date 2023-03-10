package com.example.crudphp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	ListView lvLista;
	ArrayList<String> clientes = new ArrayList<String>();
	ArrayList<String> clientesId = new ArrayList<String>();
	Map<String, ArrayList<String>> clientesD = new HashMap<String,ArrayList<String>>(); 
	private ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLista= (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
        lvLista.setAdapter(adapter);
        lvLista.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position,
        			long id) {
        		Intent IrCad = new Intent(MainActivity.this, CadastroActivity.class);
        		String cId = clientesId.get(position);
                IrCad.putExtra("id", cId);
                IrCad.putExtra("dados", clientesD.get(cId));
                startActivityForResult(IrCad, 0);
                //startActivity(IrCad);
        	}
		});
       try {
			ConexaoMysql("select","select * from cliente;",false);
		} catch (UnsupportedEncodingException e) {
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent intent){
    	clientes.clear();
		clientesId.clear();
		clientesD.clear();
		adapter.clear();
		adapter.notifyDataSetChanged();
		try {
			ConexaoMysql("select","select * from cliente;",false);
		} catch (UnsupportedEncodingException e) {
		
		}
		
    }
    public void cadastrar(View v)
    {
    	Intent IrCad = new Intent(MainActivity.this,CadastroActivity.class);
    	IrCad.putExtra("id", "0");
    	startActivity(IrCad);
    }
    
    public void ConexaoMysql(String acao,String query, boolean alertar) throws UnsupportedEncodingException{
    	String data = URLEncoder.encode("acao","UTF-8")+"="+URLEncoder.encode(acao,"UTF-8");
    	data += "&"+URLEncoder.encode("query","UTF-8")+"="+URLEncoder.encode(query,"UTF-8");
    	
    	String resposta = "";
    	BufferedReader respostaServidor = null;
    	
    	 try {
             URL url = new URL("http://10.0.2.2/querymysql.php");
             URLConnection conexao = url.openConnection(); 
             conexao.setDoOutput(true); 
             
             OutputStreamWriter bufferDados = new OutputStreamWriter(conexao.getOutputStream()); 
             bufferDados.write( data );
             bufferDados.flush();    
             
             respostaServidor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
             StringBuilder respostaRecebida = new StringBuilder();
             String linhaRecebidaResposta = null;

             while((linhaRecebidaResposta = respostaServidor.readLine()) != null)
             {
             	if(acao=="select")
             	{
             		JSONObject response;
     	    		try
     	    		{
     	    			response = new JSONObject(linhaRecebidaResposta);
     	    			JSONArray main = response.optJSONArray("dados");
     	    			for(int i = 0; i < main.length(); i++)
     	    			{
     	    				JSONObject child = main.getJSONObject(i);
     	    				if(child.length()>0)
     	    				{
	     	    				ArrayList<String> infos = new ArrayList<String>();
	     	    				infos.add(child.optString("nome"));
	     	    				infos.add(child.optString("endereco"));
	     	    				infos.add(child.optString("telefone"));
	     	    				infos.add(child.optString("rg"));
	     	    				infos.add(child.optString("cpf"));
	     	    				infos.add(child.optString("nascimento"));
	     	    				clientesD.put(child.optString("id"),infos);
	     	    				clientes.add(child.optString("nome"));
	     	    				clientesId.add(child.optString("id"));
	     	    			
     	    				}
     	    			}
     	    		}
     	    		catch(JSONException e)
     	    		{
     	    			resposta = e.getMessage();
     	    			Toast.makeText(getApplicationContext(), "Erro chamada ao Web Service:"+e.getMessage(), 2000).show();
     	    		}
             	}
             }
             resposta = respostaRecebida.toString();
         }
         catch(Exception ex)
         {
        	 AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
	         messageBox.setMessage(ex.getMessage());
	         messageBox.setTitle("Resultado");
	         messageBox.setNeutralButton("OK", null);
	         messageBox.show();
         }
         finally
         {
             try
             {
                 respostaServidor.close();
             }

             catch(Exception ex) {}
         }
         if(alertar)
         {
	         AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
	         messageBox.setMessage(resposta);
	         messageBox.setTitle("Resultado");
	         messageBox.setNeutralButton("OK", null);
	         messageBox.show();
         }
    }
}
