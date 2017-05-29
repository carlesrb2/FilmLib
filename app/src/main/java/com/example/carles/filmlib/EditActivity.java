package com.example.carles.filmlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditActivity  extends AppCompatActivity implements LoadJSONTask.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        String URL = "http://162.243.214.157/android/getElemento.php?id=" + b.get("id");
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<Elemento> elementosList) {
        ArrayList<Elemento> aElementos = new ArrayList<Elemento>();
        for (Elemento ele : elementosList) {
            EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
            txtTitulo.setText(ele.getTitulo());
        }

        AdapterElemento adapter = new AdapterElemento(this, aElementos);

    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }
}
