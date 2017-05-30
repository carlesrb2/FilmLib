package com.example.carles.filmlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity implements LoadJSONTask.Listener {

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

            EditText txtGenero = (EditText) findViewById(R.id.txtGenero);
            txtGenero.setText(ele.getGenero());

            EditText txtEstreno = (EditText) findViewById(R.id.txtEstreno);
            txtEstreno.setText(ele.getEstreno());
            EditText txtDirector = (EditText) findViewById(R.id.txtDirector);
            EditText txtTemporadas = (EditText) findViewById(R.id.txtTemporadas);

            TextView lbDirector = (TextView) findViewById(R.id.lbDirector);
            TextView lbTemporadas = (TextView) findViewById(R.id.lbTemporadas);

            if (ele.getTipo().equals("0")) {
                txtDirector.setText(ele.getDirector());
                txtTemporadas.setVisibility(View.INVISIBLE);
                txtDirector.setVisibility(View.VISIBLE);
                lbDirector.setVisibility(View.VISIBLE);
                lbTemporadas.setVisibility(View.INVISIBLE);


            } else {
                txtTemporadas.setText(ele.getTemporadas());
                txtTemporadas.setVisibility(View.VISIBLE);
                txtDirector.setVisibility(View.INVISIBLE);
                lbDirector.setVisibility(View.INVISIBLE);
                lbTemporadas.setVisibility(View.VISIBLE);
            }
        }

        AdapterElemento adapter = new AdapterElemento(this, aElementos);

    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }
}
