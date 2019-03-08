package com.dianatorres.icesi.examen1_dianatorres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CanjeActivity extends AppCompatActivity {

    private TextView puntosSumados;
    private int puntosTotales;
    private int masUno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        puntosSumados=(TextView) findViewById(R.id.puntosSumados);
        masUno=Integer.parseInt(getIntent().getExtras().getString("sumarPuntos"));
        puntosTotales=Integer.parseInt(puntosSumados.getText().toString())+1;

        puntosSumados.setText(puntosTotales+"");




    }
}
