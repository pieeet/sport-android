package com.rocdev.android.sport;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rocdev.piet.sport.backend.lidApi.model.Lid;

/**
 * Created by piet on 01-11-15.
 */
public class LidLijstLayout extends RelativeLayout {
    TextView naamTextView;
    TextView woonplaatsTextView;

    Context context;

    public LidLijstLayout(Context context) {
        super(context);
    }

    public LidLijstLayout(Context context, Lid lid) {
        super(context);
        this.context = context;
        //inflate layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lid_listview_item, this, true);
        naamTextView = (TextView) findViewById(R.id.naamTextView);
        woonplaatsTextView = (TextView) findViewById(R.id.woonplaatsTextView);
        naamTextView.setText(lid.getNaam());
        woonplaatsTextView.setText(lid.getWoonplaats());
//        setLid(lid);

    }

    public void setLid(Lid lid) {
        naamTextView.setText(lid.getNaam());
        woonplaatsTextView.setText(lid.getWoonplaats());
    }
}
