package com.example.taserfan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.dto.BiciDTO;
import com.example.taserfan.dto.CocheDTO;
import com.example.taserfan.dto.Model;
import com.example.taserfan.dto.MotoDTO;
import com.example.taserfan.dto.PatineteDTO;
import com.example.taserfan.dto.VehiculoDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddVehicle extends AppCompatActivity  /*extends BaseActivity*/{

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
    private String tipoVehiculo;
    EditText matricula, marca, precio, bateria, fecha, desc;
    Spinner coloresspinner, estadospinner, carnetspinner;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        ImageView iconadd = findViewById(R.id.icon_add);
        TextView nombretipo = findViewById(R.id.nombrevehiculo);
         coloresspinner = findViewById(R.id.colorvehiculo);
         estadospinner = findViewById(R.id.estadovehiculo);
         carnetspinner = findViewById(R.id.carnetvehiculo);

        matricula = findViewById(R.id.matricula);
        marca = findViewById(R.id.marcavehiculo);
         precio = findViewById(R.id.preciovehiculo);
         bateria = findViewById(R.id.bateriavehiculo);
         fecha = findViewById(R.id.fechavehiculo);
         desc = findViewById(R.id.descvehiculo);

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

        if (getIntent().getStringExtra("matricula")!=null){
            matricula.setText(getIntent().getStringExtra("matricula"));
            marca.setText(getIntent().getStringExtra("marca"));
            precio.setText(getIntent().getIntExtra("preciohora", 0)+"");
            desc.setText(getIntent().getStringExtra("desc"));
            bateria.setText(getIntent().getIntExtra("bateria",0)+"");
            fecha.setText(getIntent().getStringExtra("fecha"));
        }


        tipoVehiculo = getIntent().getStringExtra("vehiculo");
        if (tipoVehiculo.equals("coche")){
            iconadd.setImageResource(R.mipmap.ic_coche_foreground);
            nombretipo.setText("Coche");
            puertas.setVisibility(View.VISIBLE);
            plazas.setVisibility(View.VISIBLE);
            colores.remove("negro");

        } else if (tipoVehiculo.equals("moto")){
            iconadd.setImageResource(R.mipmap.ic_moto_foreground);
            nombretipo.setText("Moto");
            velocidad.setVisibility(View.VISIBLE);
            cilindrada.setVisibility(View.VISIBLE);
            colores.remove("rojo");
            colores.remove("amarillo");

        } else if (tipoVehiculo.equals("bici")){
            iconadd.setImageResource(R.mipmap.ic_bici_foreground);
            nombretipo.setText("Bici");
            List<String> tipos= new ArrayList<>();
            tipos.add("Montaña");
            tipos.add("Paseo");
            tipos.add("Hibrida");

            tipobici.setVisibility(View.VISIBLE);
            ArrayAdapter adapterbicitipo = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos);
            tipobici.setAdapter(adapterbicitipo);
        } else if (tipoVehiculo.equals("patinete")){
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

    public void addVehiculolista(View view){
        switch (tipoVehiculo) {
            case "coche":
                CocheDTO coche = new CocheDTO(matricula.getText().toString(),
                        marca.getText().toString(),
                        coloresspinner.getSelectedItem().toString(),
                        Integer.parseInt(precio.getText().toString()),
                        desc.getText().toString(),
                        Integer.parseInt(bateria.getText().toString())
                        , fecha.getText().toString(),
                        carnettransformer(carnetspinner.getSelectedItem().toString()),
                        estadospinner.getSelectedItem().toString(),
                        Integer.parseInt(plazas.getText().toString()),
                        Integer.parseInt(puertas.getText().toString()));
                Model.getInstance().getVehiculos().add(coche);
                /*
                executeCall(new CallInterface(){
                                @Override
                                public void doInBackground() {
                                    result= Connector.getConector().post(CocheDTO.class, coche, API.Routes.COCHE);
                                }

                                @Override
                                public void doInUI() {
                                    if (!(result instanceof Result.Success)){
                                        Result.Error error = (Result.Error) result;
                                        extras.crearDialogos(this, "Error", error.getError());
                                    }
                                }
                 */

                break;
            case "moto":
                MotoDTO MOTO = new MotoDTO(matricula.getText().toString(),
                        marca.getText().toString(),
                        coloresspinner.getSelectedItem().toString(),
                        Integer.parseInt(precio.getText().toString()),
                        desc.getText().toString(),
                        Integer.parseInt(bateria.getText().toString())
                        , fecha.getText().toString(),
                        carnettransformer(carnetspinner.getSelectedItem().toString()),
                        estadospinner.getSelectedItem().toString(),
                        Integer.parseInt(velocidad.getText().toString()),
                        Integer.parseInt(cilindrada.getText().toString()));
                Model.getInstance().getVehiculos().add(MOTO);
                /*
                executeCall(new CallInterface(){
                                @Override
                                public void doInBackground() {
                                    result= Connector.getConector().post(MotoDTO.class, moto, API.Routes.MOTO);
                                }

                                @Override
                                public void doInUI() {
                                    if (!(result instanceof Result.Success)){
                                        Result.Error error = (Result.Error) result;
                                        extras.crearDialogos(this, "Error", error.getError());
                                    }
                                }
                 */
                break;
            case "bici":
                Model.getInstance().getVehiculos().add(new BiciDTO(matricula.getText().toString(),
                        marca.getText().toString(),
                        coloresspinner.getSelectedItem().toString(),
                        Integer.parseInt(precio.getText().toString()),
                        desc.getText().toString(),
                        Integer.parseInt(bateria.getText().toString())
                        , fecha.getText().toString(),
                        carnettransformer(carnetspinner.getSelectedItem().toString()),
                        estadospinner.getSelectedItem().toString(),
                        tipobici.getSelectedItem().toString()));
                break;
            case "patinete":
                Model.getInstance().getVehiculos().add(new PatineteDTO(matricula.getText().toString(),
                        marca.getText().toString(),
                        coloresspinner.getSelectedItem().toString(),
                        Integer.parseInt(precio.getText().toString()),
                        desc.getText().toString(),
                        Integer.parseInt(bateria.getText().toString())
                        , fecha.getText().toString(),
                        carnettransformer(carnetspinner.getSelectedItem().toString()),
                        estadospinner.getSelectedItem().toString(),
                        Integer.parseInt(tamanio.getText().toString()),
                        Integer.parseInt(ruedas.getText().toString())));
                break;
        }
        Intent intent = new Intent(this, Recycler.class);
        startActivity(intent);
    }

    private int carnettransformer(String tipocarnet){
        switch(tipocarnet){
            case "NO":
                return 0;
            case "AM":
                return 1;
            case "A":
                return 2;
            case "B":
                return 3;
            case "C":
                return 4;
            case "D":
                return 5;
            case "E":
                return 6;
            case "Z":
                return 9;
            default: return 0;
        }
    }


}