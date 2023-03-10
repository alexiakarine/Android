package com.example.exerciciopagamento;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText txtMensalidade,txtPagamento;
	TextView tvResultado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMensalidade = (EditText)findViewById(R.id.txtMensalidade);
        txtPagamento = (EditText)findViewById(R.id.txtPagamento);
        tvResultado = (TextView)findViewById(R.id.tvResultado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Calcular(View v){
    	Double mensalidade,pagamento,desconto;
    	mensalidade = Double.parseDouble(txtMensalidade.getText().toString());
    	pagamento = Double.parseDouble(txtPagamento.getText().toString());
    	
    	if(pagamento<=5) desconto=0.1;
    	else if(pagamento<=9) desconto=0.05;
    	else if(pagamento==10) desconto=0.0;
    	else desconto=-0.02;
    	mensalidade-=(desconto*mensalidade);
    	tvResultado.setText("Mensalidade: "+mensalidade+"\nDesconto: "+(desconto*100)+"\n"+(desconto<0?"Você foi multado por pagar atrasado":""));
    }
}
