package com.example.projeto_aula05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaLogin extends AppCompatActivity {
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        btnEntrar = findViewById(R.id.BTNEFETUARLOGIN);

        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent efetuarLogin = new Intent(getApplicationContext(), TelaAccount.class);
                startActivity(efetuarLogin);
            }
        });
    }
}