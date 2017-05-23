package com.example.carles.filmlib;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadJSONTask extends AsyncTask<String, Void, Response> {

    public LoadJSONTask(Listener listener) {

        mListener = listener;
    }

    public interface Listener {

        void onLoaded(List<Elemento> androidList);

        void onError();
    }

    private Listener mListener;

    @Override
    protected Response doInBackground(String... strings) {
        try {
            Response r = new Response();
            Gson gson = new Gson();
            String stringResponse = loadJSON(strings[0]);
            ArrayList<Elemento> ae = new ArrayList<Elemento>();

            try {
                JSONArray array = new JSONArray(stringResponse);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    Elemento a = gson.fromJson(row.toString(), Elemento.class);
                    Log.i("EL", a.toString());
                    ae.add(a);

                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            r.setElementos(ae);
            return r;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response response) {

        if (response != null) {

            mListener.onLoaded(response.getElementos());

        } else {

            mListener.onError();
        }
    }

    private String loadJSON(String jsonURL) throws IOException {

        URL url = new URL(jsonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = in.readLine()) != null) {

            response.append(line);
        }

        in.close();
        Log.i("JSON", response.toString());
        return response.toString();

    }
}