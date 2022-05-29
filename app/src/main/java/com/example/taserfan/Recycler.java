package com.example.taserfan;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.taserfan.DTO.VehiculoDTO;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

import java.util.List;

public class Recycler extends BaseActivity implements CallInterface {

    private RecyclerView recyclerView;
    private List<VehiculoDTO> lista;
    private RadioButton todos;
    private RadioButton coche;
    private RadioButton moto;
    private RadioButton patinete;
    private RadioButton bici;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Button addvehicle = findViewById(R.id.add);
        Button addcoche = findViewById(R.id.addcoche);
        Button addmoto = findViewById(R.id.addmoto);
        Button addbici = findViewById(R.id.addbici);
        Button addpatinete = findViewById(R.id.addpatinete);

//        recyclerView.findViewById(R.id.pauwu);
//        recyclerView.setAdapter(customAdapter);
        recyclerView.findViewById(R.id.pauwu);

        customAdapter = new CustomAdapter(this, lista);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);

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
    }

    @Override
    public void doInBackground() {
        todos.setChecked(true);
        String url;
        if (moto.isChecked()) {
            url = "Moto";
        } else if (coche.isChecked()){
            url = "Coche";
        } else if (bici.isChecked()){
            url = "Bici";
        }
        else if (patinete.isChecked()){
            url = "Patinete";
        } else {
            url = "Vehiculo";
        }
        lista = connector.getAsList(VehiculoDTO.class, url);
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