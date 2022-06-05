package com.example.taserfan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.taserfan.dto.LoginDTO;
import com.example.taserfan.dto.Model;
import com.example.taserfan.preferencias.PreferenciasActivity;
import com.example.taserfan.preferencias.SetupTema;

public class MainActivity extends AppCompatActivity {


    private boolean logeado;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         username = findViewById(R.id.user);
         password = findViewById(R.id.password);


        SetupTema.applyPreferenceTheme(getApplicationContext());

    }

    private boolean comprobarlogin(String user, String pass){
        LoginDTO aux;
        for (int i =0; i<Model.getInstance().getUsers().size(); i++){
            aux = Model.getInstance().getUsers().get(i);
            if (aux.getUser().equals(user) && aux.getPassword().equals(pass))
                return logeado=true;
        }
            return logeado=false;
    }

    public void login(View view) {
        comprobarlogin(username.getText().toString(), password.getText().toString());
        if (logeado) {
            Intent intent = new Intent(this, Recycler.class);
            startActivity(intent);
        } else {
            extras.crearDialogos(this, "Error", "Usuario y/o Contraseña no válidos");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), PreferenciasActivity.class);
        startActivity(intent);
        return true;
    }


}