package com.example.executarasync;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void executarTarefa(View v) {
		SegundoPlano sp = new SegundoPlano();
		sp.execute("Mensagem");
		//ExecutarTarefa tarefa = new ExecutarTarefa();
		//tarefa.execute("");
	}

	public class SegundoPlano extends AsyncTask<String, String, Integer> {
		private  ProgressDialog Dialogo;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			Dialogo = new ProgressDialog(MainActivity.this);
			Dialogo.setTitle("Titulo");
			Dialogo.setMessage("Oi, tudo bem?");
			Dialogo.setIndeterminate(false);
			Dialogo.show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			for(int i = 0; i<10; i++)
			{
				publishProgress(params[0],""+i,""+10);//1
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e){
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			Dialogo.setMessage("Carregando: "+values[1]+"/"+values[2]+" "+values[0]);
		}

		@Override
		protected void onPostExecute(Integer result) {
			Dialogo.setMessage("Finalizado");
			Dialogo.dismiss();
		}

	}

	// Classe que implementa a execu��o Async
	public class ExecutarTarefa extends AsyncTask<String, Integer, Integer> {
		private ProgressDialog mProgressDialog;

		// Met�do que � executado antes do processo em background ser executad
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Cria uma caixa de progresso
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Define um titulo da caixa
			mProgressDialog.setTitle("Realizando tarefa em Background");
			// Define uma mensagem inicial, indicando processamento em
			// background
			mProgressDialog.setMessage("Carregando...");
			mProgressDialog.setIndeterminate(false);
			// Mostra a caixa de progresso
			mProgressDialog.show();
		}

		// Met�do que executa tafera em segundo plano
		@Override
		protected Integer doInBackground(String... params) {
			// La�o simulando tarefa
			for (int i = 0; i < 50; i++) {
				// Dispara uma mensagem para atualizar o andamento do processo
				publishProgress(i); // Atualiza o update
				// Espera um tempo
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			return null;
		}

		// Met�do que atualiza o andamento do processo em background
		@Override
		protected void onProgressUpdate(Integer... values) {

			mProgressDialog.setMessage("Executando: " + values[0]);
		}

		// Met�do executado ap�s a finaliza��o da tarefa em background
		@Override
		protected void onPostExecute(Integer result) {
			// Envia uma mensagem e fecha o dialogo
			mProgressDialog.setMessage("Processo Finalizado!");
			mProgressDialog.dismiss();
		}
	}
}
