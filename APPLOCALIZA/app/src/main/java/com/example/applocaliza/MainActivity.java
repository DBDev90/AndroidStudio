package com.example.applocaliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity implements LocationListener {
    LocationManager local;
    Button chamaTelaLatLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chamaTelaLatLong = findViewById(R.id.BTNINICIO);

        chamaTelaLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTelaLat = new Intent(getApplicationContext(), TelaLatLong.class);
                startActivity(chamaTelaLat);
            }
        });
    }

    public void getLocal(View v) {
        try {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
                return;
            }

            local = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            local.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
        }catch (SecurityException e){
            Toast.makeText(this, "Problemas no GPS: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        EditText lat = findViewById(R.id.edLatitude);
        EditText lon = findViewById(R.id.edLongitude);

        enviarSMS(location);
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

    public void enviarSMS (Location l) {
        EditText numero  = findViewById(R.id.edTelefone);
        String n = numero.getText().toString();

        double lat = l.getLatitude();
        double lon = l.getLongitude();

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(n, null, String.valueOf(lat +','+ lon), null, null);
            Toast.makeText(this, "ENVIADO", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "FALHA", Toast.LENGTH_LONG).show();
        }
    }
}