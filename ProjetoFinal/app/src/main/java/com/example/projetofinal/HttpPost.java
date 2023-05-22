package com.example.projetofinal;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPost extends AsyncTask<String, Void, String> {

    private static final String TAG = "AsyncPostRequest";

    private String url;
    private String data;

    public HttpPost(String url, String data) {
        this.url = url;
        this.data = data;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(data.length()));
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                response = new String(bytes);
            } else {
                Log.e(TAG, "Error response code: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error sending POST request: " + e.getMessage());
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        // Do something with the response
    }
}
