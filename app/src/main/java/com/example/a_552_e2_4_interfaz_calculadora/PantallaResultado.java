package com.example.a_552_e2_4_interfaz_calculadora;

import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class PantallaResultado {
    StringBuffer sResultado; //hacer métodos con lo que tiene, string
    TextView mResultado; //lo que muestra

    public PantallaResultado(TextView mResultado) {  //constructor que recibe el textview
        this.mResultado = mResultado;
        Iniciar();
    }

    public void Iniciar () { //inicializa la pantalla en 0
        sResultado = new StringBuffer("0");
        mResultado.setText(sResultado);
    }

    public void Clear () { //Limpia la pantalla
        sResultado = new StringBuffer("");
        mResultado.setText(sResultado);
    }


    public boolean append (String sValor){
        if (sResultado.toString().equals("0") && !sValor.equals(".")){
            Clear();
        }
        sResultado.append(sValor);
        mResultado.setText(sResultado);
        return true;
        //validar cuando ya hay un punto
    }

    public boolean isin0 () {
        if (sResultado.toString().equals("0")){
            return true;
        }
        else {
            return false;
        }
    }

    public double get() {
        return Double.parseDouble(sResultado.toString());
    }

    public void set (double dRes){
        sResultado = new StringBuffer(dRes+"");

        //fOrmato numérico
        NumberFormat fResultado = NumberFormat.getNumberInstance(new Locale("es", "MX"));
        fResultado.setMaximumFractionDigits(4);
        mResultado.setText(fResultado.format(dRes));
    }
}

