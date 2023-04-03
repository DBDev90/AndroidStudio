package com.example.projeto_aula05;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonChamaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonChamaLogin = findViewById(R.id.BTNLOGIN);

        buttonChamaLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent chamaLogin = new Intent(getApplicationContext(), TelaLogin.class);
                startActivity(chamaLogin);
            }
        });
    }
}