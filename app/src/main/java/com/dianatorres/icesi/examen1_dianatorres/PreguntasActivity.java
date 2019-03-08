package com.dianatorres.icesi.examen1_dianatorres;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private int n1;
    private int n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Random rand = new Random();
         n1 = rand.nextInt(100);
        op1=(TextView) findViewById(R.id.op1);
        op1.setText(n1+"");

        n2 = rand.nextInt(100);
        op2=(TextView) findViewById(R.id.op2);
        op2.setText(n2+"");

        operadores.add("+");
        operadores.add("-");
        operadores.add("X");
        operadores.add("/");

        int randomGenerator = rand.nextInt(3);
        func=(TextView) findViewById(R.id.func);
        func.setText(operadores.get(randomGenerator)+"");

        btn_validar=(Button) findViewById(R.id.btn_validar);
        btn_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNum = Integer.parseInt(respuesta.getText().toString());

                if(func.equals("+")){
                    if(myNum==(n1+n2)){
                       validar();
                    }
                }
                else if(func.equals("-")){

                    if(myNum==(n1-n2)){
                        validar();
                    }
                }
                else if(func.equals("X")){
                    if(myNum==(n1*n2)){
                        validar();
                    }
                }
                else if(func.equals("/")){
                    if(myNum==(n1/n2)){
                        validar();
                    }
                }
                else{

                }
            }
        });

    }

    public void validar(){
        Intent intent = new Intent(PreguntasActivity.this, CanjeActivity.class);
        intent.putExtra("sumarPuntos",1);
        startActivity(intent);

    }


}
