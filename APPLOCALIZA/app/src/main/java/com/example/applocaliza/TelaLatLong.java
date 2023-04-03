package com.example.applocaliza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaLatLong extends AppCompatActivity {

    Button chamaTelaTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lat_long);

        chamaTelaTel = findViewById(R.id.BTNCONFIGURA);

        chamaTelaTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTelaTel = new Intent(getApplicationContext(), TelaSMS.class);
                startActivity(chamaTelaTel);
            }
        });
    }
}