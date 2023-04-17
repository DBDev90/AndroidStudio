package com.example.projeto_webview_ponte;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
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

    @JavascriptInterface
    public void enviaSMS (String n, String m) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(n, null, m, null, null);
            Toast.makeText(context, "ENVIADO", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "FALHA", Toast.LENGTH_SHORT).show();
        }
    }
}