package com.exmple.intentputextra;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Tela2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        Intent navegar = getIntent();
        String msg;
        msg = navegar.getStringExtra("msg");
        //Toast.makeText(getApplicationContext(), "Mensagem: "+msg, 2000).show();
        TextView txtMensagem = (TextView)findViewById(R.id.textView1);
        txtMensagem.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tela2, menu);
        return true;
    }
    
    public void irTela1(View v){
    	finish();
    }
}
