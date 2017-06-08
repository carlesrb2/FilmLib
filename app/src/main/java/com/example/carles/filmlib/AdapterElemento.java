package com.example.carles.filmlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Carles on 14/05/2017.
 */

public class AdapterElemento extends BaseAdapter implements View.OnClickListener {

    protected Activity activity;
    protected ArrayList<Elemento> arrayElementos;

    public AdapterElemento(Activity activity, ArrayList<Elemento> arrayElementos) {
        this.activity = activity;
        this.arrayElementos = arrayElementos;
    }

    @Override
    public int getCount() {
        return arrayElementos.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayElementos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_element, null);
        }

        Elemento ele = arrayElementos.get(position);

        ImageView iFav = (ImageView) v.findViewById(R.id.imgFav);
        ImageView iView = (ImageView) v.findViewById(R.id.imgView);

        if (ele.getFavorita().equals("1")) {
            iFav.setVisibility(View.VISIBLE);
        } else {
            iFav.setVisibility(View.INVISIBLE);
        }

        if (ele.getVista().equals("1")) {
            iView.setVisibility(View.VISIBLE);
        } else {
            iView.setVisibility(View.INVISIBLE);
        }
        TextView titulo = (TextView) v.findViewById(R.id.titulo);
        titulo.setText(ele.getTitulo());

        TextView genero = (TextView) v.findViewById(R.id.genero);
        genero.setText(ele.getGenero());

        TextView estreno = (TextView) v.findViewById(R.id.estreno);
        estreno.setText("(" + ele.getEstreno() + ")");

        if (ele.getTipo().equals("0")) {
            TextView directortemp = (TextView) v.findViewById(R.id.directortemp);
            directortemp.setText(ele.getDirector());
        } else {
            TextView directortemp = (TextView) v.findViewById(R.id.directortemp);
            directortemp.setText(ele.getTemporadas() + " Temporadas");
        }

        v.setTag(position);
        v.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Elemento e = (Elemento) object;

        Log.i("Click", e.getTitulo());
        Intent i = new Intent(activity, EditActivity.class);
        i.putExtra("id", e.getId());
        i.putExtra("tipo", e.getTipo());
        activity.startActivity(i);


    }

    public void clear() {
        arrayElementos.clear();
    }
}
