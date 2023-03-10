package com.example.calculadoraa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.editText);
        num2 = (EditText) findViewById(R.id.editText3);
        resultado = (EditText) findViewById(R.id.editText2);
    }

    public void somar(View v)
    {
        Double n1, n2, result;
        n1 = Double.parseDouble(num1.getText().toString());
        n2 = Double.parseDouble(num2.getText().toString());
        //result = Double.parseDouble(resultado.getText().toString());
        result = n1 + n2;
        resultado.setText(result.toString());
    }

    public void subtrair (View v)
    {

        Double n1, n2, result;

        n1 = Double.parseDouble(num1.getText().toString());
        n2 = Double.parseDouble(num2.getText().toString());
        //result = Double.parseDouble(resultado.getText().toString());
        result = n1 - n2;
        resultado.setText(result.toString());
    }


    public void multiplicar (View v)
    {

        Double n1, n2, result;

        n1 = Double.parseDouble(num1.getText().toString());
        n2 = Double.parseDouble(num2.getText().toString());
        //result = Double.parseDouble(resultado.getText().toString());
        result = n1 * n2;
        resultado.setText(result.toString());
    }

    public void dividir (View v)
    {

        Double n1, n2, result;

        n1 = Double.parseDouble(num1.getText().toString());
        n2 = Double.parseDouble(num2.getText().toString());
        //result = Double.parseDouble(resultado.getText().toString());
        result = n1/n2;
        resultado.setText(result.toString());
    }

}

