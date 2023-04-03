package com.example.projetoconverseranosluzquilometros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText anosLuzEditText, quilometrosEditText;
    private Button anosLuzParaQuilometrosButton, quilometrosParaAnosLuzButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anosLuzEditText = findViewById(R.id.anosLuzEditText);
        quilometrosEditText = findViewById(R.id.quilometrosEditText);
        anosLuzParaQuilometrosButton = findViewById(R.id.anosLuzParaQuilometrosButton);
        quilometrosParaAnosLuzButton = findViewById(R.id.quilometrosParaAnosLuzButton);

        anosLuzParaQuilometrosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anosLuzString = anosLuzEditText.getText().toString();
                if (!anosLuzString.isEmpty()) {
                    double anosLuz = Double.parseDouble(anosLuzString);
                    double quilometros = anosLuz * 9.4607305;
                    quilometrosEditText.setText(quilometros + "");
                }
            }
        });

        quilometrosParaAnosLuzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quilometrosString = quilometrosEditText.getText().toString();
                if (!quilometrosString.isEmpty()) {
                    double quilometros = Double.parseDouble(quilometrosString);
                    double anosLuz = quilometros / 9.4607305;
                    anosLuzEditText.setText(anosLuz + "");
                }
            }
        });
    }
}