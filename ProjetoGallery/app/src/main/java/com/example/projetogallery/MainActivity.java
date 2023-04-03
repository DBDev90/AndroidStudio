package com.example.projetogallery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView chamaGaleriaUm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chamaGaleriaUm = findViewById(R.id.peopleUm);

        chamaGaleriaUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTelaLat = new Intent(getApplicationContext(), GaleriaUm.class);
                startActivity(chamaTelaLat);
            }
        });
    }
}