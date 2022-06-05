package com.example.taserfan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.dto.BiciDTO;
import com.example.taserfan.dto.CocheDTO;
import com.example.taserfan.dto.Model;
import com.example.taserfan.dto.MotoDTO;
import com.example.taserfan.dto.PatineteDTO;
import com.example.taserfan.dto.VehiculoDTO;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Recycler extends BaseActivity implements CallInterface, View.OnClickListener{

    private RecyclerView recyclerView;
    private List<VehiculoDTO> lista;
    private RadioButton todos;
    private RadioButton coche;
    private RadioButton moto;
    private RadioButton patinete;
    private RadioButton bici;
    CustomAdapter customAdapter;
    private EditText texto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Button addvehicle = findViewById(R.id.add);
        Button addcoche = findViewById(R.id.addcoche);
        Button addmoto = findViewById(R.id.addmoto);
        Button addbici = findViewById(R.id.addbici);
        Button addpatinete = findViewById(R.id.addpatinete);

        recyclerView=findViewById(R.id.pauwu);
        lista = Model.getInstance().getVehiculos();
        customAdapter = new CustomAdapter(this, lista);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        customAdapter.setOnClickListener(this);
        recyclerView.setAdapter(customAdapter);


        texto = findViewById(R.id.editTextTextPersonName);

        coche = findViewById(R.id.coche);
        todos = findViewById(R.id.todos);
        moto = findViewById(R.id.moto);
        patinete = findViewById(R.id.patinete);
        bici = findViewById(R.id.bici);

        todos.setChecked(true);
        addvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addcoche.getVisibility()==View.VISIBLE){
                    addcoche.setVisibility(View.INVISIBLE);
                    addbici.setVisibility(View.INVISIBLE);
                    addmoto.setVisibility(View.INVISIBLE);
                    addpatinete.setVisibility(View.INVISIBLE);
                } else {
                    addcoche.setVisibility(View.VISIBLE);
                    addbici.setVisibility(View.VISIBLE);
                    addmoto.setVisibility(View.VISIBLE);
                    addpatinete.setVisibility(View.VISIBLE);
                }
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                VehiculoDTO aux = Model.getInstance().getVehiculos().get(viewHolder.getAdapterPosition());

                int pos = viewHolder.getAdapterPosition();
                Model.getInstance().getVehiculos().remove(pos);
                customAdapter.notifyItemRemoved(pos);
                Toast.makeText(getApplicationContext(), "vehiculo eliminado", Toast.LENGTH_SHORT).show();
                /*
                String auxx="";
                if (aux.getTipovehiculo()=="coche")
                    auxx=url+ API.Routes.COCHE;
                else if (aux.getTipovehiculo()=="moto")
                    auxx=url+ API.Routes.MOTO;
                else if (aux.getTipovehiculo()=="bici")
                    auxx=url+ API.Routes.BICI;
                else if (aux.getTipovehiculo()=="patinete")
                    auxx = url+API.Routes.PATINETE;
            }

            executeCall(new CallInterface(){
                @Override
                public void doInBackground() {
                    lista= Connector.getConector().delete(VehiculoDTO.class, auxx+"?matricula="+aux.getMatricula());
                }

                @Override
                public void doInUI() {
                    customAdapter.setNewData(lista);
                }

                 */
            };
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onClick(View view) {
        VehiculoDTO u = Model.getInstance().getVehiculos().get(recyclerView.getChildAdapterPosition(view));

        Intent intent = new Intent(this, AddVehicle.class);
        intent.putExtra("matricula", u.getMatricula());
        intent.putExtra("marca", u.getMarca());
        intent.putExtra("preciohora", u.getPreciohora());
        intent.putExtra("desc", u.getDescripcion());
        intent.putExtra("bateria", u.getBateria());
        intent.putExtra("fecha", u.getFecha());
        intent.putExtra("vehiculo", u.getTipovehiculo());
        intent.putExtra("pos", recyclerView.getChildAdapterPosition(view));
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
    }

    @Override
    public void doInBackground() {

        /*
        String url = "Vehiculo";
        lista = connector.getAsList(VehiculoDTO.class, url);
         */

    }

    public void recyclerclick(View view) {
        VehiculoDTO u = Model.getInstance().getVehiculos().get(recyclerView.getChildAdapterPosition(view));

        Intent intent = new Intent(this, AddVehicle.class);
        intent.putExtra("matricula", u.getMatricula());
        intent.putExtra("marca", u.getMarca());
        intent.putExtra("color", u.getColor());
        intent.putExtra("preciohora", u.getPreciohora());
        intent.putExtra("desc", u.getDescripcion());
        intent.putExtra("bateria", u.getBateria());
        intent.putExtra("fecha", u.getFecha());
        intent.putExtra("carnet", u.getCarnettipo());
        intent.putExtra("estado", u.getEstado());
        intent.putExtra("vehiculo", u.getTipovehiculo());
        intent.putExtra("pos", recyclerView.getChildAdapterPosition(view));
        startActivity(intent);
    }

    public void filtros (View view){

        if (todos.isChecked()) {
            lista = Model.getInstance().getVehiculos();
            customAdapter.setNewData(lista);
        } else if (coche.isChecked()) {
            List<VehiculoDTO> coches = Model.getInstance().getVehiculos().stream()
                    .filter(v -> v.getTipovehiculo().equals("coche")).collect(Collectors.toList());
            lista = coches;
            customAdapter.setNewData(lista);
        } else if (moto.isChecked()){
            List<VehiculoDTO> motos = Model.getInstance().getVehiculos().stream()
                    .filter(v -> v.getTipovehiculo().equals("moto")).collect(Collectors.toList());
            lista = motos;
            customAdapter.setNewData(lista);
        } else if (bici.isChecked()){
            List<VehiculoDTO> bicis = Model.getInstance().getVehiculos().stream()
                    .filter(v -> v.getTipovehiculo().equals("bici")).collect(Collectors.toList());
            lista = bicis;
            customAdapter.setNewData(lista);
        } else if (patinete.isChecked()){
            List<VehiculoDTO> patinetes = Model.getInstance().getVehiculos().stream()
                    .filter(v -> v.getTipovehiculo().equals("patinete")).collect(Collectors.toList());
            lista = patinetes;
            customAdapter.setNewData(lista);
        }

        texto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<VehiculoDTO> aux = lista;
                List<VehiculoDTO> list = aux.stream()
                        .filter(v -> v.getMarca().contains(editable.toString()))
                        .collect(Collectors.toList());
                lista = list;
                customAdapter.setNewData(lista);
            }
        });


    }


    @Override
    public void doInUI() {
        customAdapter.setNewData(lista);
    }

    public void aniadircoche (View view){
        Intent intent = new Intent(this, AddVehicle.class);
        intent.putExtra("vehiculo", "coche");
        startActivity(intent);
    }

    public void aniadirmoto (View view){
        Intent intent = new Intent(this, AddVehicle.class);
        intent.putExtra("vehiculo", "moto");
        startActivity(intent);
    }

    public void aniadirbici (View view){
        Intent intent = new Intent(this, AddVehicle.class);
        intent.putExtra("vehiculo", "bici");
        startActivity(intent);
    }

    public void aniadirpatinete (View view){
        Intent intent = new Intent(this, AddVehicle.class);
        intent.putExtra("vehiculo", "patinete");
        startActivity(intent);
    }

}