package com.dianatorres.icesi.examen1_dianatorres;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PreguntasActivity extends AppCompatActivity {

    private TextView op1;
    private TextView func;
    private TextView op2;

    private ArrayList operadores=new ArrayList();

    private Button btn_validar;

    private EditText respuesta;

    private double myNum;

    private double respuestaCorrecta;

    private int n1;
    private int n2;

    private Intent intentCanje;

    private int puntosSumados;

    private Button btn_canje;

    private String dificultadPreguntas;

    private RadioGroup rdg_preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        puntosSumados=0;

        operadores.add("+");
        operadores.add("-");
        operadores.add("X");
        operadores.add("/");

        dificultadPreguntas="";
        dificultadPreguntas=getIntent().getExtras().getString("dificultadPreguntas");

        generarPregunta();


        btn_validar=(Button) findViewById(R.id.btn_validar);
        btn_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNum = Integer.parseInt(respuesta.getText().toString());



//                rdg_preguntas= (RadioGroup) findViewById(R.id.rdg);
//                rdg_preguntas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        if (checkedId == R.id.rd_opcion1) {
//
//                        } else if (checkedId == R.id.rd_opcion2) {
//
//                        } else if (checkedId == R.id.rd_opcion3) {
//
//                        } else if (checkedId == R.id.rd_opcion4) {
//
//
//                        }
//                    }
//                });

                if(func.getText().toString().equals("+")){
                    if(myNum==(n1+n2)){
                       validadoSumarPuntos();

                    }else{
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Fallaste!", Toast.LENGTH_LONG);
                        toast1.show();
                    }
                }
                else if(func.getText().toString().equals("-")){

                    if(myNum==(n1-n2)){
                        validadoSumarPuntos();
                    }else{
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Fallaste!", Toast.LENGTH_LONG);
                        toast1.show();
                    }
                }
                else if(func.getText().toString().equals("X")){
                    if(myNum==(n1*n2)){
                        validadoSumarPuntos();
                    }else{
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Fallaste!", Toast.LENGTH_LONG);
                        toast1.show();
                    }
                }
                else if(func.getText().toString().equals("/")){
                    if(((int)myNum)==(n1/n2)){
                        validadoSumarPuntos();
                    }else{
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Fallaste!", Toast.LENGTH_LONG);
                        toast1.show();
                    }
                }
                else{
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Pregunta mal generada", Toast.LENGTH_LONG);
                    toast1.show();
                }
            }
        });

        btn_canje=(Button) findViewById(R.id.btn_canje);
        btn_canje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCanjeActivity();
            }
        });

    }

    public void generarPregunta(){

        if(!dificultadPreguntas.equals("")){

            if(dificultadPreguntas.equals("dificil")){
                respuesta=(EditText) findViewById(R.id.respuesta);

                Random rand = new Random();
                n1 = rand.nextInt(100);
                op1=(TextView) findViewById(R.id.op1);
                op1.setText(n1+"");

                n2 = rand.nextInt(100);
                op2=(TextView) findViewById(R.id.op2);
                op2.setText(n2+"");

                final int randomGenerator = rand.nextInt(3);
                func=(TextView) findViewById(R.id.func);
                func.setText(operadores.get(randomGenerator)+"");

            }else if(dificultadPreguntas.equals("facil")){

                respuesta=(EditText) findViewById(R.id.respuesta);

                Random rand = new Random();
                n1 = rand.nextInt(30);
                op1=(TextView) findViewById(R.id.op1);
                op1.setText(n1+"");

                n2 = rand.nextInt(30);
                op2=(TextView) findViewById(R.id.op2);
                op2.setText(n2+"");

                final int randomGenerator = rand.nextInt(4);
                func=(TextView) findViewById(R.id.func);
                func.setText(operadores.get(randomGenerator)+"");

            }

//            RadioButton r1= (RadioButton) findViewById(R.id.rd_opcion1);
//            RadioButton r2= (RadioButton) findViewById(R.id.rd_opcion2);
//            RadioButton r3= (RadioButton) findViewById(R.id.rd_opcion3);
//            RadioButton r4= (RadioButton) findViewById(R.id.rd_opcion4);
//
//            if(func.getText().toString().equals("+")){
//
//                respuestaCorrecta=n1+n2;
//                r1.setText(respuestaCorrecta);
//
//
//            }
//            else if(func.getText().toString().equals("-")){
//
//                respuestaCorrecta=n1-n2;
//            }
//            else if(func.getText().toString().equals("X")){
//
//                respuestaCorrecta=n1*n2;
//            }
//            else if(func.getText().toString().equals("/")){
//
//                respuestaCorrecta=(int) n1/n2;
//            }



        }

    }



    public void validadoSumarPuntos(){

        if(!dificultadPreguntas.equals("")){

            if(dificultadPreguntas.equals("dificil")) {
                puntosSumados=puntosSumados+20;
                Toast toast1 = Toast.makeText(getApplicationContext(),"CORRECTO! Presiona Canje para ver tus puntos!", Toast.LENGTH_LONG);
                toast1.show();
                generarPregunta();
            }else if(dificultadPreguntas.equals("facil")){
                puntosSumados=puntosSumados+5;
                Toast toast1 = Toast.makeText(getApplicationContext(),"CORRECTO! Presiona Canje para ver tus puntos!", Toast.LENGTH_LONG);
                toast1.show();
                generarPregunta();
            }
        }


    }

    public void abrirCanjeActivity(){
//        Log.e("puntossssSumm",puntosSumados+"");
        intentCanje = new Intent(PreguntasActivity.this, CanjeActivity.class);
        intentCanje.putExtra("sumarPuntos",puntosSumados+"");
        startActivity(intentCanje);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode>=0){
            puntosSumados=requestCode;
            Log.e("puntossssSumm",puntosSumados+"");
            Log.e("requestCodeeeeee",requestCode+"");
        }

    }
}
