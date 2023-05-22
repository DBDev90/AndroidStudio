package com.marcus.fatec.httppost;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
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
    public int insereUsuario(String nome, String email, String senha, String fone) {
        ArrayList<Par> dados = new ArrayList<Par>();
        dados.add(new Par("nome", nome));
        dados.add(new Par("email", email));
        dados.add(new Par("senha", senha));
        dados.add(new Par("fone", fone));

        HttpPost tarefa = new HttpPost(dados);

        tarefa.setListener(new HttpPost.Listener(){
            @Override
            public void onResult(String resultado) {
                mensagem(resultado);
                Log.d("Resultado", resultado);
            }
        });

        Log.i("POST", "Executando Tarefa");
        tarefa.execute("http://biosensing.web10f40.kinghost.net/android/grava.php");
        return 0;
    }

}