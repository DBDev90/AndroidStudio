package com.example.projeto_webview_ponte;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView w = findViewById(R.id.wvTela);
        WebSettings conf = w.getSettings();
        conf.setJavaScriptEnabled(true);

        w.setWebChromeClient(new WebChromeClient());
        w.setWebViewClient(new WebViewClient());

        w.loadUrl("file:///android_asset/index.html");

        w.addJavascriptInterface(new Ponte(this), "Android");
    }
}

class Ponte{
    Context context;
    public Ponte(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void mensagem(String data){
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }
}