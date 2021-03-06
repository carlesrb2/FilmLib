package com.example.carles.filmlib;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class PeliculasActivity extends AppCompatActivity implements LoadJSONTask.Listener, NavigationView.OnNavigationItemSelectedListener {

    private ListView mListView;


    public static String tipo = "0";

    private static String URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=0&direccion=1";

    private List<HashMap<String, String>> mElementosMapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EditActivity.class);
                i.putExtra("id", "0");
                i.putExtra("tipo", Integer.parseInt(tipo));
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mListView = (ListView) findViewById(R.id.mainList);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadJSONTask(this).execute(URL);

    }

    @Override
    public void onLoaded(List<Elemento> elementosList) {
        ArrayList<Elemento> aElementos = new ArrayList<Elemento>();
        for (Elemento ele : elementosList) {

            aElementos.add(ele);
        }

        loadListView();
        AdapterElemento adapter = new AdapterElemento(this, aElementos);

        mListView.setAdapter(adapter);

    }

    @Override
    public void onError() {


        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    private void loadListView() {


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.peliculas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nombre_asc) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=1&direccion=1";
            new LoadJSONTask(this).execute(URL);
        }

        if (id == R.id.genero_asc) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=3&direccion=1";
            new LoadJSONTask(this).execute(URL);
        }

        if (id == R.id.fecha_asc) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=2&direccion=1";
            new LoadJSONTask(this).execute(URL);
        }

        if (id == R.id.nombre_desc) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=1&direccion=0";
            new LoadJSONTask(this).execute(URL);
        }

        if (id == R.id.genero_desc) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=3&direccion=0";
            new LoadJSONTask(this).execute(URL);
        }

        if (id == R.id.fecha_desc) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=2&direccion=0";
            new LoadJSONTask(this).execute(URL);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_peliculas) {
            tipo = "0";
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=0&direccion=1";
            new LoadJSONTask(this).execute(URL);
        } else if (id == R.id.nav_series) {
            tipo = "1";
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=0&direccion=1";

            new LoadJSONTask(this).execute(URL);

        } else if (id == R.id.nav_favoritas) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=0&direccion=1&favorita=1";

            new LoadJSONTask(this).execute(URL);

        } else if (id == R.id.nav_vistas) {
            URL = "http://162.243.214.157/android/getPeliculas.php?tipo=" + tipo + "&order=0&direccion=1&vista=1";

            new LoadJSONTask(this).execute(URL);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
