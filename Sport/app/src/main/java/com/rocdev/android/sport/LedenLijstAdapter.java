package com.rocdev.android.sport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.rocdev.piet.sport.backend.lidApi.LidApi;
import com.rocdev.piet.sport.backend.lidApi.model.Lid;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by piet on 01-11-15.
 * Adapter die wordt gebruikt in ListLedenActivity klasse
 *
 */
public class LedenLijstAdapter extends BaseAdapter implements View.OnClickListener{
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
        LidLijstItemLayout layout;
        Lid lid = leden.get(position);
        if (convertView == null) {
            layout = new LidLijstItemLayout(context, lid);
        } else {
            layout = (LidLijstItemLayout) convertView;
            layout.setLid(lid);
        }
        layout.setTag(lid.getSpelerscode());
        layout.setOnClickListener(this);
        return layout;
    }


    @Override
    public void onClick(View v) {
        String spelerscode = v.getTag().toString();
        new LidHaler().execute(spelerscode);


    }

    class LidHaler extends AsyncTask<String, Void, Lid> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Laden...");
            pDialog.setTitle("Even geduld");
            pDialog.show();
        }

        @Override
        protected Lid doInBackground(String... params) {
            Lid lid = null;
            String spelerscode = params[0];
            LidApi.Builder builder = new LidApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sportbackend2.appspot.com/_ah/api/");

            LidApi api = builder.build();
            try {
                lid = api.getLid(spelerscode).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lid;
        }

        @Override
        protected void onPostExecute(Lid lid) {
            Intent intent = new Intent(context, WijzigLidActivity.class);
            intent.putExtra("spelerscode", lid.getSpelerscode());
            intent.putExtra("roepnaam", lid.getRoepnaam());
            intent.putExtra("tussenvoegsels", lid.getTussenvoegsels());
            intent.putExtra("achternaam", lid.getAchternaam());
            intent.putExtra("adres", lid.getAdres());
            intent.putExtra("postcode", lid.getPostcode());
            intent.putExtra("woonplaats", lid.getWoonplaats());
            intent.putExtra("telefoon", lid.getTelefoon());
            intent.putExtra("geboortedatum", lid.getGeboortedatum());
            intent.putExtra("geslacht", lid.getGeslacht());
            intent.putExtra("email", lid.getEmail());
            pDialog.dismiss();
            context.startActivity(intent);
        }
    }

}
