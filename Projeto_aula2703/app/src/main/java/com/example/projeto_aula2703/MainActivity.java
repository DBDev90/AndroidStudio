package com.example.projeto_aula2703;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
LocationManager local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getLocal(View v){
        try {
            local = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            local.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException e){
            Toast.makeText(this, "Problemas no GPS:" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        EditText lat = findViewById(R.id.editTextLatitude);
        EditText lon = findViewById(R.id.editTextLongitude);

        lat.setText(""+location.getLatitude());
        lon.setText(""+location.getLongitude());
    }



    @Override
    public void onProviderEnabled(String s){
        Toast.makeText(this, "Voce ativou o GPS", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s){
        Toast.makeText(this, "Ative o GPS", Toast.LENGTH_LONG).show();
    }

}