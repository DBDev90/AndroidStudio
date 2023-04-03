package com.example.projetoenviosms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviar (View v) {
        EditText numero  = findViewById(R.id.editTextNumero);
        EditText mensagem= findViewById(R.id.editTextMensagem);

        String n = numero.getText().toString();
        String m = mensagem.getText().toString();

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(n, null, m, null, null);
            Toast.makeText(this, "ENVIADO", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "FALHA", Toast.LENGTH_LONG).show();
        }
    }
}