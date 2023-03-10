package com.example.cadastro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText edtNome,edtRG,edtCPF,edtIdade,edtEmail,edtTelefone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtNome=(EditText)findViewById(R.id.editText1);
        edtRG=(EditText)findViewById(R.id.editText2);
        edtCPF=(EditText)findViewById(R.id.editText3);
        edtIdade=(EditText)findViewById(R.id.editText4);
        edtEmail=(EditText)findViewById(R.id.editText5);
        edtTelefone=(EditText)findViewById(R.id.editText6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Cadastrar(View v) throws UnsupportedEncodingException{
    	String nome = edtNome.getText().toString().trim();
    	String rg = edtRG.getText().toString().trim();
    	String cpf = edtCPF.getText().toString().trim();
    	String idade = edtIdade.getText().toString().trim();
    	String email = edtEmail.getText().toString().trim();
    	String telefone = edtTelefone.getText().toString().trim();
    	
    	String data = URLEncoder.encode("nome","UTF-8")+"="+URLEncoder.encode(nome,"UTF-8");
    	data += "&"+URLEncoder.encode("rg","UTF-8")+"="+URLEncoder.encode(rg,"UTF-8");
    	data += "&"+URLEncoder.encode("cpf","UTF-8")+"="+URLEncoder.encode(cpf,"UTF-8");
    	data += "&"+URLEncoder.encode("idade","UTF-8")+"="+URLEncoder.encode(idade,"UTF-8");
    	data += "&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
    	data += "&"+URLEncoder.encode("telefone","UTF-8")+"="+URLEncoder.encode(telefone,"UTF-8");

    	String resposta = "";
    	BufferedReader respostaServidor = null;
    	
    	 try {
             URL url = new URL("http://10.0.2.2/cadastro.php");
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
                    respostaRecebida.append(linhaRecebidaResposta + "\n");
                    
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
               
        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setMessage(resposta);
        messageBox.setTitle("Resultado");
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
        
        Intent navegar = new Intent(MainActivity.this,Cadastrados.class);
        startActivity(navegar);
    }
}
