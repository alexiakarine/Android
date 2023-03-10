package com.example.avaliacao02;

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
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText edtMatricula,edtNome,edtDepartamento,edtSalario,edtFaltas,edtHorasExtras,edtConvenioMedico,edtRG,edtCPF;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtMatricula=(EditText)findViewById(R.id.editText1);
        edtNome=(EditText)findViewById(R.id.editText2);
        edtDepartamento=(EditText)findViewById(R.id.editText3);
        edtSalario=(EditText)findViewById(R.id.editText4);
        edtFaltas=(EditText)findViewById(R.id.editText5);
        edtHorasExtras=(EditText)findViewById(R.id.editText6);
        edtConvenioMedico=(EditText)findViewById(R.id.editText7);
        edtRG=(EditText)findViewById(R.id.editText8);
        edtCPF=(EditText)findViewById(R.id.editText9);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void cadastro(View v){
    	try {
			Cadastrar();
		} catch (UnsupportedEncodingException e) {
			AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
	        messageBox.setMessage("Falha ao executar!");
	        messageBox.setTitle("Resultados");
	        messageBox.setNeutralButton("OK", null);
	        messageBox.show();
		}
    }
    
    public void Cadastrar() throws UnsupportedEncodingException{
    	String matricula = edtMatricula.getText().toString().trim();
    	String nome = edtNome.getText().toString().trim();
    	String departamento = edtDepartamento.getText().toString().trim();
    	String salario = edtSalario.getText().toString().trim();
    	String faltas = edtFaltas.getText().toString().trim();
    	String horasextras = edtHorasExtras.getText().toString().trim();
    	String conveniomedico = edtConvenioMedico.getText().toString().trim();
    	String rg = edtRG.getText().toString().trim();
    	String cpf = edtCPF.getText().toString().trim();
    	
    	double faltasd = Double.parseDouble(faltas);
    	double horasextrasd = Double.parseDouble(horasextras);
    	double salariototal = Double.parseDouble(salario);
    	
    	//faltasd = (faltasd*8);
    	
    	double salariophora = ((salariototal/30)/8);
    	
    	double salariod = salariototal;
    	salariod-= faltasd*salariophora;
    	salariod+= horasextrasd*salariophora;
    	
    	String data = URLEncoder.encode("matricula","UTF-8")+"="+URLEncoder.encode(matricula,"UTF-8");
    	data += "&"+URLEncoder.encode("nome","UTF-8")+"="+URLEncoder.encode(nome,"UTF-8");
    	data += "&"+URLEncoder.encode("departamento","UTF-8")+"="+URLEncoder.encode(departamento,"UTF-8");
    	data += "&"+URLEncoder.encode("salariototal","UTF-8")+"="+URLEncoder.encode(salario,"UTF-8");
    	data += "&"+URLEncoder.encode("salario","UTF-8")+"="+URLEncoder.encode(""+salariod,"UTF-8");
    	data += "&"+URLEncoder.encode("faltas","UTF-8")+"="+URLEncoder.encode(faltas,"UTF-8");
    	data += "&"+URLEncoder.encode("horasextras","UTF-8")+"="+URLEncoder.encode(horasextras,"UTF-8");
    	data += "&"+URLEncoder.encode("conveniomedico","UTF-8")+"="+URLEncoder.encode(conveniomedico,"UTF-8");
    	data += "&"+URLEncoder.encode("rg","UTF-8")+"="+URLEncoder.encode(rg,"UTF-8");
    	data += "&"+URLEncoder.encode("cpf","UTF-8")+"="+URLEncoder.encode(cpf,"UTF-8");

    	String resposta = "";
    	BufferedReader respostaServidor = null;
    	
    	 try {
             URL url = new URL("http://10.0.2.2/cadastrar.php");
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
        messageBox.setTitle("Resultados");
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }
}
