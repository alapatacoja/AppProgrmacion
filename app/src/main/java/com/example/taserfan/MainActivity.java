package com.example.taserfan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean logeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logeado=true;
        Button login = findViewById(R.id.loginbtn);

    }


    public void login(View view) {
        if (logeado) {
            Intent intent = new Intent(this, Recycler.class);
            startActivity(intent);
        }
    }
}