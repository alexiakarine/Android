package com.example.phpmysqlandroid;

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
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrar(View v) throws UnsupportedEncodingException{
        // repassa informações das variáveis para posterior cadastro
        
        
        EditText edUsername;
        // recupera nome da caixa de texto e associa com variável
        edUsername = (EditText) findViewById(R.id.editText1);
        EditText edPassword;
        // recupera nome da caixa de texto e associa com variável
        edPassword = (EditText) findViewById(R.id.editText2);
        
        //passar para as strings
        String userName = edUsername.getText().toString(); 
        String passWord = edPassword.getText().toString();
        
       
        // Cria String informações que irá conter os dados a serem enviados no padrão de protocolo HTTP              
        String informacoes = URLEncoder.encode("username", "UTF-8") 
                       + "=" + URLEncoder.encode(userName, "UTF-8"); 

          
        informacoes += "&" + URLEncoder.encode("password", "UTF-8") 
                      + "=" + URLEncoder.encode(passWord, "UTF-8");
        
           
          
        String resposta = "";
          // criar buffer de memória para enviar e receber resutlado do HTTP
        BufferedReader respostaServidor=null;

        // Envia a informação (dados) 
        try {          	
            // Defined URL  que receberá os dados
            URL url = new URL("http://10.0.2.2/cadastro.php");            
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
        
            // Read Server Response
            while((linhaRecebidaResposta = respostaServidor.readLine()) != null)
            {
                   // Append server response in string
                   respostaRecebida.append(linhaRecebidaResposta + "/n");
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
       messageBox.setTitle("Retorno script php");
       messageBox.setNeutralButton("OK", null);
       messageBox.show();
       
    	
    }
    
    
    public void consultar(View v) throws UnsupportedEncodingException{
        // repassa informações das variáveis para posterior cadastro
        String acao = "listar"; 
        
        EditText edUsername;
        // recupera nome da caixa de texto e associa com variável
        edUsername = (EditText) findViewById(R.id.editText1);
        EditText edPassword;
        // recupera nome da caixa de texto e associa com variável
        edPassword = (EditText) findViewById(R.id.editText2);
        
        //passar para as strings
        String userName = edUsername.getText().toString(); 
        String passWord = edPassword.getText().toString();
        
       
        // Cria String informações que irá conter os dados a serem enviados no padrão de protocolo HTTP              
        String informacoes = URLEncoder.encode("username", "UTF-8") 
                       + "=" + URLEncoder.encode(userName, "UTF-8"); 

          
        informacoes += "&" + URLEncoder.encode("password", "UTF-8") 
                      + "=" + URLEncoder.encode(passWord, "UTF-8");
        
          
          
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
        
            // Read Server Response
            while((linhaRecebidaResposta = respostaServidor.readLine()) != null)
            {
                   // Append server response in string
                   respostaRecebida.append(linhaRecebidaResposta);
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
              
        // Show response on activity (mostra a resposta)
       
        // obter resposta dividida
        String[] campos = resposta.split(",");
        
        
        
       AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
       messageBox.setMessage(resposta);
       messageBox.setMessage(campos[0].toString()+":"+campos[1].toString());
       messageBox.setTitle("Retorno script php");
       messageBox.setNeutralButton("OK", null);
       messageBox.show();
       
       if(!campos[1].toString().equals("Não permitido")){
    	   Intent navegar = new Intent(MainActivity.this, Tela2.class);
    	   startActivity(navegar);
    	   
       }

    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
        return true;
    }
}
