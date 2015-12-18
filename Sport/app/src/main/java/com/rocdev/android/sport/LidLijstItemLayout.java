package com.rocdev.android.sport;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rocdev.piet.sport.backend.lidApi.model.Lid;

/**
 * Created by piet on 01-11-15.
 * Layout (lid_listview_item.xml) die wordt gebruikt in de LedenLijstAdapter klasse
 */
public class LidLijstItemLayout extends RelativeLayout {
    TextView naamTextView;
    TextView woonplaatsTextView;

    public LidLijstItemLayout(Context context) {
        super(context);
    }

    public LidLijstItemLayout(Context context, Lid lid) {
        super(context);



        //boilerplate code (gewoon kopieren...)
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lid_listview_item, this, true);

        naamTextView = (TextView) findViewById(R.id.naamTextView);
        woonplaatsTextView = (TextView) findViewById(R.id.woonplaatsTextView);
        setLid(lid);

    }

    public void setLid(Lid lid) {
        naamTextView.setText(lid.getNaam());
        woonplaatsTextView.setText(lid.getWoonplaats());
    }


}
