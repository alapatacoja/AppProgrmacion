package com.example.taserfan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddVehicle extends AppCompatActivity {

    List<String> colores = new ArrayList<>();
    List<String> estado = new ArrayList<>();
    List<String> carnet = new ArrayList<>();
    private EditText velocidad;
    private EditText cilindrada;
    private Spinner tipobici;
    private EditText puertas;
    private EditText plazas;
    private EditText ruedas;
    private EditText tamanio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        ImageView iconadd = findViewById(R.id.icon_add);
        TextView nombretipo = findViewById(R.id.nombrevehiculo);
        Spinner coloresspinner = findViewById(R.id.colorvehiculo);
        Spinner estadospinner = findViewById(R.id.estadovehiculo);
        Spinner carnetspinner = findViewById(R.id.carnetvehiculo);

        puertas = findViewById(R.id.puertascoche);
        plazas = findViewById(R.id.asientoscoche);
        tipobici = findViewById(R.id.tipobici);
        velocidad = findViewById(R.id.velocidadmoto);
        cilindrada = findViewById(R.id.cilindradamoto);
        ruedas = findViewById(R.id.ruedaspatinete);
        tamanio = findViewById(R.id.tamanyopatinete);

        puertas.setVisibility(View.INVISIBLE);
        plazas.setVisibility(View.INVISIBLE);
        tipobici.setVisibility(View.INVISIBLE);
        velocidad.setVisibility(View.INVISIBLE);
        cilindrada.setVisibility(View.INVISIBLE);
        ruedas.setVisibility(View.INVISIBLE);
        tamanio.setVisibility(View.INVISIBLE);

        addcolores();
        addestado();
        addcarnet();

        ArrayAdapter <String> adapterColores  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colores);
        coloresspinner.setAdapter(adapterColores);

        ArrayAdapter <String> adapterestado  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        estadospinner.setAdapter(adapterestado);

        ArrayAdapter <String> adaptercarnet  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carnet);
        carnetspinner.setAdapter(adaptercarnet);

        String tipo = getIntent().getStringExtra("vehiculo");
        if (tipo.equals("coche")){
            iconadd.setImageResource(R.mipmap.ic_coche_foreground);
            nombretipo.setText("Coche");
            puertas.setVisibility(View.VISIBLE);
            plazas.setVisibility(View.VISIBLE);
            colores.remove("negro");

        } else if (tipo.equals("moto")){
            iconadd.setImageResource(R.mipmap.ic_moto_foreground);
            nombretipo.setText("Moto");
            velocidad.setVisibility(View.VISIBLE);
            cilindrada.setVisibility(View.VISIBLE);
            colores.remove("rojo");
            colores.remove("amarillo");

        } else if (tipo.equals("bici")){
            iconadd.setImageResource(R.mipmap.ic_bici_foreground);
            nombretipo.setText("Bici");
            List<String> tipos= new ArrayList<>();
            tipos.add("Montaña");
            tipos.add("Paseo");
            tipos.add("Hibrida");

            tipobici.setVisibility(View.VISIBLE);
            ArrayAdapter adapterbicitipo = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos);
            tipobici.setAdapter(adapterbicitipo);
        } else if (tipo.equals("patinete")){
            iconadd.setImageResource(R.mipmap.ic_patinete_foreground);
            nombretipo.setText("Patinete");
            ruedas.setVisibility(View.VISIBLE);
            tamanio.setVisibility(View.VISIBLE);
        }

    }

    public void addcolores(){
        colores.add("verde");
        colores.add("rojo");
        colores.add("blanco");
        colores.add("negro");
        colores.add("azul");
        colores.add("amarillo");
    }

    public void addcarnet(){
        carnet.add("NO");
        carnet.add("AM");
        carnet.add("A");
        carnet.add("B");
        carnet.add("C");
        carnet.add("D");
        carnet.add("E");
        carnet.add("Z");
    }

    public void addestado(){
        estado.add("preparado");
        estado.add("en taller");
        estado.add("baja");
        estado.add("alquilado");
        estado.add("reservado");
    }



}