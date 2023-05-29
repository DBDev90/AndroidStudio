package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        WebView web = (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("file:///android_asset/index.html");


        web.addJavascriptInterface(new Ponte(this), "Android");
    }
}

class Ponte {
    Context context;
    public Ponte(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void mensagem(String data) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public int insereUsuario(String nome, String email, String senha, String fone) throws JSONException {
        String dados = new JSONObject()
                .put("nome", nome)
                .put("email", email)
                .put("senha", senha)
                .put("fone", fone)
                .toString();
        String resposta="";
        try {
            URL url = new URL("http://www.datalakeanalytic.com/si/grava.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setDoOutput(true);
            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.println(dados);

            connection.connect();

            resposta = new Scanner(connection.getInputStream()).next();
            mensagem (resposta);

        } catch (Exception e) {
          Log.d("[Douglas]", e.toString() + " Resposta:" + resposta);
          mensagem ("Verifique sua conexão");
        }
        return 0;
    }

}