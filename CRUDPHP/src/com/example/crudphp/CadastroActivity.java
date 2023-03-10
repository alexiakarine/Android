package com.example.crudphp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import android.R.bool;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends Activity {
	EditText edtNome,edtRG,edtCPF,edtTelefone,edtNascimento,edtEndereco,edtId;
	Button btnAlterar,btnExcluir;
	int id = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        
        edtNome = (EditText)findViewById(R.id.editText1);
        edtNascimento = (EditText)findViewById(R.id.editText2);
        edtTelefone = (EditText)findViewById(R.id.editText3);
        edtEndereco = (EditText)findViewById(R.id.editText4);
        edtRG = (EditText)findViewById(R.id.editText5);
        edtCPF = (EditText)findViewById(R.id.editText6);
        
        btnAlterar = (Button)findViewById(R.id.button1);
        btnExcluir = (Button)findViewById(R.id.button2);
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        if(id==0)
        {
        	btnAlterar.setText("Cadastrar");
        	btnExcluir.setVisibility(View.GONE);
        }
        else
        {
        	ArrayList<String> dados = (ArrayList<String>)intent.getStringArrayListExtra("dados");
        	edtNome.setText(dados.get(0));
            edtNascimento.setText(dados.get(5));
            edtTelefone.setText(dados.get(2));
            edtEndereco.setText(dados.get(1));
            edtRG.setText(dados.get(3));
            edtCPF.setText(dados.get(4));
        }
    }
    public void Alterar(View v)
    {
    	if(id==0)//CRIAR
    	{
    		String query = "insert into cliente(nome,nascimento,endereco,rg,cpf,telefone) values('"+edtNome.getText().toString()+
    				"','"+edtNascimento.getText().toString()+
    				"','"+edtEndereco.getText().toString()+
    				"','"+edtRG.getText().toString()+
    				"','"+edtCPF.getText().toString()+
    				"','"+edtTelefone.getText().toString()+
    				"');";
    		 try {
				boolean retorno = ConexaoMysql("insert",query);
				String r = "";
				if(retorno) r="Criado com sucesso!";
				else r="Falha ao criar!";
				AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
				messageBox.setMessage(r);
				messageBox.setTitle("Resultado");
				messageBox.setNeutralButton("OK", null);
				messageBox.show();
				if(retorno){
					Intent navegar = new Intent();
			    	setResult(1,navegar);
			    	finish();
				}
    		 } catch (UnsupportedEncodingException e) {
 				
    		 }
    	}
    	else
    	{
    		String query = "update cliente set nome='"+edtNome.getText().toString()+
    				"',nascimento='"+edtNascimento.getText().toString()+
    				"',endereco='"+edtEndereco.getText().toString()+
    				"',rg='"+edtRG.getText().toString()+
    				"',cpf='"+edtCPF.getText().toString()+
    				"',telefone='"+edtTelefone.getText().toString()+
    				"' where id="+id+";";
    		 try {
				boolean retorno = ConexaoMysql("update",query);
				String r = "";
				if(retorno) r="Alterado com sucesso!";
				else r="Falha ao alterar!";
				AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
				messageBox.setMessage(r);
				messageBox.setTitle("Resultado");
				messageBox.setNeutralButton("OK", null);
				messageBox.show();
				if(retorno){
					Intent navegar = new Intent();
			    	setResult(1,navegar);
			    	finish();
				}
			} catch (UnsupportedEncodingException e) {
				
			}
    	}
    }
    public void Excluir(View v)
    {
    	if(id!=0)
    	{
    		String query = "delete from cliente where id="+id+";";
    		 try {
				boolean retorno = ConexaoMysql("delete",query);
				String r = "";
				if(retorno==true) r="Deletado com sucesso!";
				else r="Falha ao deletar!";
				AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
				messageBox.setMessage(r);
				messageBox.setTitle("Resultado");
				messageBox.setNeutralButton("OK", null);
				messageBox.show();
				if(retorno){
					Intent navegar = new Intent();
			    	setResult(1,navegar);
			    	finish();
				}
			} catch (UnsupportedEncodingException e) {
				
			}
    	}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastro, menu);
        return true;
    }
    
    public boolean ConexaoMysql(String acao,String query) throws UnsupportedEncodingException{
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
        if(resposta.contains("1")) return true;
        else return false;
    }
}
