package com.example.matias.comovoyamorir.muertes;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.matias.comovoyamorir.MainActivity;
import com.example.matias.comovoyamorir.R;

import java.util.Random;

public class Muertes extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muertes);

        String[] muertes;
        Button botonVolver, botonSalir;

        muertes = getResources().getStringArray(R.array.muertes);
        TextView tvMostrarMuerte = (TextView) findViewById(R.id.mostrarMuertes);
        TextView tvMostrarMuerte2 = (TextView) findViewById(R.id.mostrarMuertes2);
        TextView tvMostrarFecha = (TextView) findViewById(R.id.tvMostrarAnyo);

        Random aleatorio = new Random();
        int numAle = aleatorio.nextInt(muertes.length);

        int dia = aleatorio.nextInt(28-1)+1;
        int mes = aleatorio.nextInt(12-1)+1;
        int anyo = aleatorio.nextInt(2060-2016)+2016;


        String nombreMostrar = getIntent().getStringExtra("nombre");
        tvMostrarMuerte.setText(nombreMostrar.toUpperCase());
        tvMostrarFecha.setText(dia + "-" + mes + "-"+ anyo);
        tvMostrarMuerte2.setText(muertes[numAle]);
        botonSalir = (Button) findViewById(R.id.botonSalir);
        botonSalir.setOnClickListener(this);
        botonVolver = (Button) findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.botonVolver: {
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
            }
            case R.id.botonSalir: {
                this.finish();
                super.finish();
                super.onDestroy();
            }
        }
    }

}

