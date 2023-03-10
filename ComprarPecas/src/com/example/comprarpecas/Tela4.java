package com.example.comprarpecas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Tela4 extends Activity {
	TextView txtMemoria,txtMemoriaValor;
	TextView txtHD,txtHDValor;
	TextView txtProcessador,txtProcessadorValor;
	TextView txtResultado;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        
        Intent anterior = getIntent();
		String memoria = anterior.getStringExtra("memoria_selecionada");
		String memoria_valor = anterior.getStringExtra("memoria_valor");
		String hd = anterior.getStringExtra("hd_selecionado");
		String hd_valor = anterior.getStringExtra("hd_valor");
		String processador = anterior.getStringExtra("processador_selecionado");
		String processador_valor = anterior.getStringExtra("processador_valor");
		
		double memoriav,hdv,processadorv,total;
		
		memoriav = Double.parseDouble(memoria_valor);
		hdv = Double.parseDouble(hd_valor);
		processadorv = Double.parseDouble(processador_valor);
		
		total = (memoriav+hdv+processadorv);
		
		txtMemoria = (TextView)findViewById(R.id.textView2);
		txtMemoriaValor = (TextView)findViewById(R.id.textView3);
		txtHD = (TextView)findViewById(R.id.textView5);
		txtHDValor = (TextView)findViewById(R.id.textView6);
		txtProcessador = (TextView)findViewById(R.id.textView8);
		txtProcessadorValor = (TextView)findViewById(R.id.textView9);
		txtResultado = (TextView)findViewById(R.id.textView10);
		
		txtMemoria.setText("Nome: "+memoria);
		txtMemoriaValor.setText("Valor: R$"+memoria_valor);
		txtHD.setText("Nome: "+hd);
		txtHDValor.setText("Valor: R$"+hd_valor);
		txtProcessador.setText("Nome: "+processador);
		txtProcessadorValor.setText("Valor: R$"+processador_valor);
		txtResultado.setText("Resultado: R$"+total);
		
    }
    public void irTela1(View v){
		Intent intent = new Intent(Tela4.this,MainActivity.class);
		String valor = txtMemoriaValor.getText().toString();
		intent.putExtra("valor",valor);
		startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela4, menu);
        return true;
    }
}
