package com.example.projeto_webview_latitudelong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private Ponte ponte;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.wvTela);
        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());

        webview.loadUrl("file:///android_asset/index.html");

        ponte = new Ponte(this);
        webview.addJavascriptInterface(ponte, "Android");

    }

    @Override
    protected void onResume() {
        super.onResume();
        ponte.getLocal();
    }

    private class Ponte implements LocationListener {
        LocationManager local;
        Context context;

        public Ponte(Context context) {
            this.context = context;
        }

        public void getLocal() {
            try {

                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE}, 1);
                    return;
                }

                local = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                local.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);

            } catch (SecurityException e) {
                Toast.makeText(context, "Problemas no GPS: " + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String script = "mostraLocal('" + latitude + "', '" + longitude + "')";
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este método é chamado quando o status do provedor de localização é alterado.
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este método é chamado quando o provedor de localização é habilitado.
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este método é chamado quando o provedor de localização
        }
    }
}