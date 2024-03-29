package com.example.sqllitetentativa;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	String NomeBanco = "Escola";
	SQLiteDatabase BancoDados = null;
	
	EditText strNome,strIdade;
	Button btnCadastrar,btnConsultar,btnRemover,btnAlterar;
	int id = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnCadastrar = (Button)findViewById(R.id.button1);
        btnConsultar = (Button)findViewById(R.id.button2);
        btnRemover = (Button)findViewById(R.id.button3);
        btnAlterar = (Button)findViewById(R.id.button4);
        
        strNome = (EditText)findViewById(R.id.editText1);
        strIdade = (EditText)findViewById(R.id.editText2);
       
        
        btnCadastrar.setOnClickListener(new View.OnClickListener()
        {

			public void onClick(View v) {
				CriarBanco();
				if(strNome.getText().toString().equals("") ){
					Toast.makeText(getApplicationContext(), "Preencher nome", 2000).show();
				}
				else{
					BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE, null) ;
					ContentValues campos = new ContentValues();
					campos.put("nome", strNome.getText().toString());
					campos.put("idade", strIdade.getText().toString());
					BancoDados.insert("aluno", null, campos); 
					
					Toast.makeText(getApplicationContext(), "Registro Inserido (ok)", 2000).show();
					BancoDados.close();
				}
			}
        });
        
        btnConsultar.setOnClickListener(new View.OnClickListener()
        {

			public void onClick(View v) {
				CriarBanco();
				if(strNome.getText().toString().equals("") ){
					id=0;
					Toast.makeText(getApplicationContext(), "Preencher nome", 2000).show();
				}
				else{
					String nome;
					nome = strNome.getText().toString();
					BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE, null) ;
					Cursor c = BancoDados.query("aluno",
							new String[]{"_id", "nome","idade"},"nome=?", new String[] {nome}, null,null,null);
					if(c.getCount()>0){
						c.moveToFirst();
						id = c.getInt(0);
						strNome.setText(c.getString(1));
						strIdade.setText(c.getString(2));
					}
					else{
						Toast.makeText(getApplicationContext(), "Registro n�o encontrado", 2000).show();
						BancoDados.close();
						
					}
					
				}
			}
        });
        btnRemover.setOnClickListener(new View.OnClickListener()
        {

			public void onClick(View v) {
				CriarBanco();
				if(id==0){
					Toast.makeText(getApplicationContext(), "Realizar Conculta", 2000).show();
				}
				else{
					BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE,null);
					String _id=String.valueOf(id);
					BancoDados.delete("aluno", "_id=?", new String[]{_id});
					Toast.makeText(getApplicationContext(), "Registro Removido (OK)", 2000).show();
					BancoDados.close();
					id=0;
					strNome.setText("");
					strIdade.setText("");
				}
				}
        });
        btnAlterar.setOnClickListener(new View.OnClickListener(){
        	public void onClick (View v)
        	{
        		if(id==0){
        			Toast.makeText(getApplicationContext(), "Realizar Consulta", 2000).show();
        		}
        		else{
        			BancoDados =  openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE,null);
        			String _id=String.valueOf(id);
        			ContentValues campos = new ContentValues();
        			campos.put("nome", strNome.getText().toString());
        			campos.put("idade", strIdade.getText().toString());
        			BancoDados.update("aluno", campos,"_id=?",new String[]{_id});
        			Toast.makeText(getApplicationContext(), "Registro Alterado (OK)", 2000).show();
        			BancoDados.close();
        			id=0;
        			strNome.setText("");
        			strIdade.setText("");
        			
        			
        		}
        	}
        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void CriarBanco(){
    	try
    	{
    		BancoDados = openOrCreateDatabase(NomeBanco, MODE_WORLD_READABLE, null);
    		
    		String sQuery = "CREATE TABLE IF NOT EXISTS aluno (" +
    				"_id integer PRIMARY KEY,"+
    				"nome TEXT,"+
    				"idade TEXT)";
    		
    		BancoDados.execSQL(sQuery);
    	}
    	catch(Exception erro)
    	{
    		Toast.makeText(getApplicationContext(), "Erro ao criar banco", 2000).show();
    	}
    	finally{
    		BancoDados.close();
    	}
    }
    
    
}
