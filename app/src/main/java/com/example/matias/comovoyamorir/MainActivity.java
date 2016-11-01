package com.example.matias.comovoyamorir;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.matias.comovoyamorir.muertes.Muertes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etFecha, etLugar;
    DatePickerDialog.OnDateSetListener mostrarCalendario;
    Calendar cal;
    Spinner spinnerSexo;
    Button calcularMuerte;
    String recogerNombre, recogerLugar, recogerFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerSexo = (Spinner) findViewById(R.id.spSexo);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etLugar = (EditText) findViewById(R.id.etCiudadNacimiento);
        calcularMuerte = (Button) findViewById(R.id.calcularMuerte);
        calcularMuerte.setOnClickListener(this);
        etFecha = (EditText) findViewById(R.id.mostrarNacimiento);
        etFecha.setInputType(InputType.TYPE_NULL);
        etFecha.setOnClickListener(this);
        cal = Calendar.getInstance(TimeZone.getDefault());
        recogerNombre = etNombre.getText().toString();
        recogerLugar = etLugar.getText().toString();
        recogerFecha = etFecha.getText().toString();

        mostrarCalendario = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                abrirCalendario("dd/MM/yyyy", etFecha);
            }
        };

        String[] listaSexo = getResources().getStringArray(R.array.sexo);
        ArrayAdapter<String> adapterlistaSexo;

        adapterlistaSexo = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listaSexo);


        adapterlistaSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSexo.setAdapter(adapterlistaSexo);

        spinnerSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }


    public void abrirCalendario(String formato, EditText et) {
        SimpleDateFormat aCal = new SimpleDateFormat(formato);
        et.setText(aCal.format(cal.getTime()));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mostrarNacimiento: {
                new DatePickerDialog(this, mostrarCalendario, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                break;
            }
            case R.id.calcularMuerte: {
                Intent intentMuerte = new Intent(this, Muertes.class);
                intentMuerte.putExtra("nombre", ((EditText) findViewById(R.id.etNombre)).getText().toString());
                intentMuerte.putExtra("lugar", ((EditText) findViewById(R.id.etCiudadNacimiento)).getText().toString());
                startActivity(intentMuerte);
                break;
            }
        }
    }
}
