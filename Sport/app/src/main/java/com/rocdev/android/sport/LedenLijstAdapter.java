package com.rocdev.android.sport;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rocdev.piet.sport.backend.lidApi.model.Lid;

import java.util.ArrayList;

/**
 * Created by piet on 01-11-15.
 * Adapter die wordt gebruikt in ListLedenActivity klasse
 *
 */
public class LedenLijstAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Lid> leden;


    /**
     * Constructor die een lijst met leden accepteert
     * @param context
     * @param leden
     */
    public LedenLijstAdapter(Context context, ArrayList<Lid> leden) {
        this.context = context;
        this.leden = leden;
    }
    @Override
    public int getCount() {
        return leden.size();
    }

    @Override
    public Object getItem(int position) {
        return leden.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * In deze methode injecteer je de views waar je lijst uit bestaat
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LidLijstLayout layout;
        Lid lid = leden.get(position);
        if (convertView == null) {
            layout = new LidLijstLayout(context, lid);
        } else {
            layout = (LidLijstLayout) convertView;
            layout.setLid(lid);
        }
        return layout;
    }
}
