package com.example.projeto_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView web = (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("file:///android_asset/index.html");
        web.addJavascriptInterface(new Ponte(this), "Android");
    }
}

class Ponte{
    Context context;
    public Ponte(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public String consultar(){
        Banco banco = new Banco(context);
        ArrayList<String> listaDespesa = banco.consulta();

        String mensagem = "";
        if (listaDespesa != null){
            for(int i = 0; i < listaDespesa.size(); i++){
                mensagem += listaDespesa.get(i);
            }
        }else{
            mensagem = "Não há dados";
        }
        return mensagem;
    }

    @JavascriptInterface
    public void inserir(String local, String data, String valor){
        Banco banco = new Banco(context);
        if(banco.insereGasto(local, data, valor) > 0){
            Toast.makeText(context, "Dados Inseridos!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Problema na Inserção!", Toast.LENGTH_LONG).show();
        }
    }
}