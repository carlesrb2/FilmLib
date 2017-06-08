package com.example.carles.filmlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity implements LoadJSONTask.Listener, PostJSONTask.Listener {

    private String URL = "";
    private int tipo = 0;
    private String bdid = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        tipo = b.getInt("tipo");
        bdid = b.getString("id");
        Log.i("ID  --", b.getString("id"));
        String URL = "http://162.243.214.157/android/getElemento.php?id=" + b.get("id");
        if (!bdid.equals("0")) {
            new LoadJSONTask(this).execute(URL);
        } else {
            limpiarCampos();
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        EditText txtGenero = (EditText) findViewById(R.id.txtGenero);
        EditText txtEstreno = (EditText) findViewById(R.id.txtEstreno);
        EditText txtDirector = (EditText) findViewById(R.id.txtDirector);
        EditText txtTemporadas = (EditText) findViewById(R.id.txtTemporadas);

        //noinspection SimplifiableIfStatement
        if (id == R.id.editar_save) {
            URL = "http://162.243.214.157/android/setElemento.php?titulo=" + URLEncoder.encode(txtTitulo.getText().toString()) +
                    "&tipo=" + tipo + "&genero=" + URLEncoder.encode(txtGenero.getText().toString()) + "&estreno=" + txtEstreno.getText() +
                    "&temporadas=" + txtTemporadas.getText() + "&director=" + URLEncoder.encode(txtDirector.getText().toString()) + "&id=" + bdid;

            Log.i("URL", URL);
            new PostJSONTask(this).execute(URL);
            finish();
        }

        if (id == R.id.editar_delete) {
            URL = "http://162.243.214.157/android/deleteElemento.php?id=" + bdid;
            new PostJSONTask(this).execute(URL);
            finish();

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    public void limpiarCampos() {
        EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtTitulo.setText("");

        EditText txtGenero = (EditText) findViewById(R.id.txtGenero);
        txtGenero.setText("");

        EditText txtEstreno = (EditText) findViewById(R.id.txtEstreno);
        txtEstreno.setText("");
        EditText txtDirector = (EditText) findViewById(R.id.txtDirector);
        EditText txtTemporadas = (EditText) findViewById(R.id.txtTemporadas);

        TextView lbDirector = (TextView) findViewById(R.id.lbDirector);
        TextView lbTemporadas = (TextView) findViewById(R.id.lbTemporadas);

        if (tipo == 0) {
            txtDirector.setText("");
            txtTemporadas.setVisibility(View.INVISIBLE);
            txtDirector.setVisibility(View.VISIBLE);
            lbDirector.setVisibility(View.VISIBLE);
            lbTemporadas.setVisibility(View.INVISIBLE);


        } else {
            txtTemporadas.setText("");
            txtTemporadas.setVisibility(View.VISIBLE);
            txtDirector.setVisibility(View.INVISIBLE);
            lbDirector.setVisibility(View.INVISIBLE);
            lbTemporadas.setVisibility(View.VISIBLE);
        }
    }
}
