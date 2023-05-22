package com.marcus.fatec.httppost;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpPost extends AsyncTask<String, String, String> {
    ArrayList<Par> dados = null;
    private Listener listener;

    interface Listener {
        void onResult(String resultado);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public HttpPost(ArrayList<Par> dados) {
        if (dados != null) {
            Log.i("POST","Iniciando Construtor HttpPost");
            this.dados = dados;
        }
    }

    protected String doInBackground(String... parametros) {
        String retorno="";
        BufferedReader leitor = null;
        HttpURLConnection conexao = null;
        Log.i("POST", "iniciando...");
        try {
            URL url = new URL(parametros[0]);
            Log.i("POST", "URL=" + url.getPath());

            conexao = (HttpURLConnection) url.openConnection();

            Log.i("POST", "Conexao=" + conexao.toString());


            //enviando os dados
            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);

            Log.i("POST", "Enviando os Dados");

            OutputStreamWriter saida = new OutputStreamWriter(conexao.getOutputStream());
            Log.i("POST", "Saida=" + saida.toString());


            Log.i("POST", "enviando...");

            String envio = "";
            for (int i = 0; i < dados.size(); i++) {
                if (i!=0) envio+="&";
                envio += java.net.URLEncoder.encode(dados.get(i).getNome(), "UTF-8") + "=" +
                        java.net.URLEncoder.encode(dados.get(i).getValor(), "UTF-8");
                Log.i("POST", "envio[" + i + "]= " + envio);

            }

            saida.write(envio);
            saida.flush();
            Log.i("POST", "enviados");


            Log.i("POST", "recebendo resultado...");

            //recebendo o resultado
            int resposta = conexao.getResponseCode();
            leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder minhaString = new StringBuilder();
            String linha = null;

            while ((linha = leitor.readLine()) != null) {
                minhaString.append(linha + "\n");
            }


            Log.i("POST", "dados recebidos");

            retorno = minhaString.toString();

        } catch (Exception e) {
            Log.i("POST", "erro 1=" + e.toString());

        } finally {
            try {
                leitor.close();
                Log.i("POST", "fechando conexao...");
                if (conexao != null) {
                    conexao.disconnect();
                }
            } catch (Exception e) {
                Log.i("POST", "erro 2=" + e.getLocalizedMessage());
            }
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(String resultado) {
        // something...
        if (listener != null) {
            listener.onResult(resultado);
        }
    }
}
