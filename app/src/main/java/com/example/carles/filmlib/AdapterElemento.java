package com.example.carles.filmlib;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Carles on 14/05/2017.
 */

public class AdapterElemento extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Elemento> arrayElementos;

    @Override
    public int getCount() {
        return arrayElementos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_element, null);
        }

        Elemento dir = arrayElementos.get(position);

        TextView titulo = (TextView) v.findViewById(R.id.titulo);
        titulo.setText(dir.getTitulo());

        TextView estreno = (TextView) v.findViewById(R.id.estreno);
        estreno.setText("(" + dir.getEstreno() + ")");



        return v;
    }

    public void clear() {
        arrayElementos.clear();
    }
}
