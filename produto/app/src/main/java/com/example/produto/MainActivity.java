package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText valorProd, numParcela, pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorProd = (EditText)findViewById(R.id.editText);
        numParcela = (EditText)findViewById(R.id.editText3);
        pagar = (EditText)findViewById(R.id.editText2);
    }

    protected void Calcular(View v)
    {
        Double Vp,Np,P;
        Vp=Double.parseDouble(valorProd.getText().toString());
        Np=Double.parseDouble(numParcela.getText().toString());
        P=Double.parseDouble(pagar.getText().toString());

        if (numParcela==1)
        {
            P=(numParcela * 0.95);
            pagar.setText(P.toString());
        }

        else
        {

        }

        if (numParcela>1)
        {
            P=(numParcela*1.1);
            pagar.setText(P.toString());
        }

    }
}


