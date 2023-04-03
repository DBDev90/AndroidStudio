package com.example.projeto_aula05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaAccount extends AppCompatActivity {

    Button btnChamaInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_account);

        btnChamaInicio = findViewById(R.id.BTNCHAMAINICIO);

        btnChamaInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent voltaInicio = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(voltaInicio);
            }
        });
    }
}