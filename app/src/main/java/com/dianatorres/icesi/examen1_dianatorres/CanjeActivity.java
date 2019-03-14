package com.dianatorres.icesi.examen1_dianatorres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class CanjeActivity extends AppCompatActivity {

    private TextView puntosSumados;
    private int puntosTotales;
    private int masUno;


    private Intent preguntasActivity;


    private RadioGroup rdg;

    private Button btn_generarCodigoCanje;

    private String escogido;
    private int puntosAGastar=0;

    private TextView cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        puntosSumados=(TextView) findViewById(R.id.puntosSumados);
        String datos=getIntent().getExtras().getString("sumarPuntos");
//        Log.e("datoooooo",datos);
        puntosTotales=Integer.parseInt(datos);

//        puntosTotales=40;

        puntosSumados.setText(puntosTotales+"");


        rdg= (RadioGroup) findViewById(R.id.rdg);
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.btn_camiseta){
                    escogido="camiseta";
                    puntosAGastar=80;
                }else if(checkedId==R.id.btn_cuaderno){
                    escogido="cuaderno";
                    puntosAGastar=30;
                }else if(checkedId==R.id.btn_lapicero){
                    escogido="lapicero";
                    puntosAGastar=20;
                }else if(checkedId==R.id.btn_libreta){
                    escogido="libreta";
                    puntosAGastar=40;
                }else if(checkedId==R.id.btn_saco){
                    escogido="saco";
                    puntosAGastar=100;
                }
            }
        });

        btn_generarCodigoCanje=(Button) findViewById(R.id.btn_generarCodigoCanje);

        btn_generarCodigoCanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(puntosTotales>=puntosAGastar){
                    puntosTotales-=puntosAGastar;
                    puntosSumados.setText(puntosTotales+"");
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Has gastado "+puntosAGastar+" puntos, reclama tu producto con el código " +
                            "generado.", Toast.LENGTH_LONG);
                    toast1.show();
                    generarCodigo();

                }
            }
        });


    }

    public void generarCodigo(){

        ArrayList<String> caracteres= new ArrayList();
        caracteres.add("A");
        caracteres.add("B");
        caracteres.add("C");
        caracteres.add("D");
        caracteres.add("E");
        Random rC= new Random();
        int ind= rC.nextInt(5);

        Random rand = new Random();
        int n1 = rand.nextInt(10000);
        cod=(TextView) findViewById(R.id.cod);
        cod.setText(caracteres.get(ind)+n1+"");
    }
}
