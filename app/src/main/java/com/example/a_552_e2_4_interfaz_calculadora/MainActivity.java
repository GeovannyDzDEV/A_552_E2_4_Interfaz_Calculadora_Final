package com.example.a_552_e2_4_interfaz_calculadora;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kotlin.ResultKt;

public class MainActivity extends AppCompatActivity {
    //REFERENCIA A LAS VISTAS
    private TextView mResultado;
    float num1 = 0.0f;
    float num2 = 0.0f;
    String operacion = "";

    // VARIABLES PARA LAS OPERACIONES
    double op1, op2;
    boolean punto=false;
    int ultimaOp;  //guarda el id de la tecla que se pulse
    //referencia a la pantalla resultado
    PantallaResultado pantallaResultado;

    //constantes para indicar el tipo de la última tecla pulsada
    final int Nd=0;
    final int Num=1;
    final int Fun=2;

    int typeUltTecla= Nd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar action = getSupportActionBar();
        action.setIcon(R.mipmap.ic_launcher);
        action.setDisplayShowHomeEnabled(true);


        //instancia a las vistas
        mResultado = findViewById(R.id.resultado);
        //instancia a la pantalla
        pantallaResultado = new PantallaResultado(mResultado);

    }
   public void fnNumbers (View view){
        if (typeUltTecla==Fun)
            pantallaResultado.Iniciar();
        String sValor=((Button) view).getText().toString();
        if (!punto || !sValor.equals(".")){
            if (sValor.equals("."))
                punto = true;
            pantallaResultado.append(sValor);
            typeUltTecla = Num;
            op2=pantallaResultado.get();
        }
   }

   public void fnOperaciion (View view){
        op1 = pantallaResultado.get();
        ultimaOp = view.getId();
        typeUltTecla=Fun;
   }


    public void fnclear(View view) {
        pantallaResultado.Iniciar();
        ultimaOp = view.getId();
    }

    public void fnEquals(View view) {
        try {
            double dResultado = 0;
            switch (ultimaOp){
                case R.id.add:
                    dResultado = op1 + op2;
                    break;
                case R.id.less:
                    dResultado = op1 - op2;
                    break;
                case R.id.multi:
                    dResultado = op1 * op2;
                    break;
                case R.id.div:
                    if (op1 != 0 && op2 == 0) {
                        Toast.makeText(this, "NO es posible dividir " + op1 + "/0", Toast.LENGTH_SHORT).show();
                        pantallaResultado.Iniciar();
                    }
                    if (op1 != 0 && op2 != 0) {
                        dResultado = op1 / op2;
                    }
                    break;
                case R.id.tan:
                    dResultado = Math.tan(op1);
                    break;
                case R.id.mod:
                    dResultado = op1 % op2;
                    break;
                case R.id.sin:
                    dResultado = Math.sin(op1);
                    break;
                case  R.id.cos:
                    dResultado = Math.cos(op1);
                    break;
                case R.id.log:
                    dResultado = Math.log10(op1);
                    break;
                case R.id.sqrt:
                    dResultado = Math.sqrt(op1);
            }
            pantallaResultado.set(dResultado);
            typeUltTecla = Fun;
            op1 = dResultado; //en caso de repetir =
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error de sintaxis", Toast.LENGTH_SHORT).show();
            pantallaResultado.Iniciar();
        }

    }


    public void fnMasMenos(View view) {
        double dResultado = pantallaResultado.get()* -1;
        pantallaResultado.set(dResultado);
        op2 = pantallaResultado.get();
    }
    public void fnEuler(View view) {
        double dResultado = pantallaResultado.get();
        pantallaResultado.set(Math.E);
    }
    public void fnFact(View view){
        double dResultado = pantallaResultado.get();
        double factorial=1;
        while ( dResultado!=0) {
            factorial=factorial*dResultado;
            dResultado--;
        }
        pantallaResultado.set(factorial);

    }

    public void fnPi(View view) {

        double dResultado = pantallaResultado.get();
        pantallaResultado.set(Math.PI);

    }

    public void fnCuadrado(View view) {
         double dResultado = pantallaResultado.get();
         pantallaResultado.set(Math.pow(dResultado, 2));
    }
}