package com.rocdev.android.sport;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.rocdev.piet.sport.backend.lidApi.LidApi;
import com.rocdev.piet.sport.backend.lidApi.model.Lid;

import java.io.IOException;
import java.util.ArrayList;

public class ListLedenActivity extends AppCompatActivity {
    ListView listView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_leden);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.ledenListView);
        new LijstOphaler().execute();
    }


    /**
     * voor een netwerk taak is het verplicht een aparte thread te starten.
     * Hiervoor gebruik je de AsyncTask klasse.
     * Bij het maken van de klasse moet je aangeven wat voor type objecten je
     * gaat gebruiken tusen de vishaken.
     * In dit geval wordt er niets aan de thread meegegeven en
     * wordt er een ArrayList met leden teruggegeven als de thread is uitgevoerd
     * en die je in de onPostExecute methode kunt gebruiken.
     * zie Android cursus hoofdstuk 6
     */

    class LijstOphaler extends AsyncTask<Void, Void, ArrayList<Lid>> {

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ListLedenActivity.this);
            pDialog.setMessage("Laden...");
            pDialog.setTitle("Even geduld");
            pDialog.show();
        }

        @Override
        protected ArrayList<Lid> doInBackground(Void... params) {

            //Instantieer de api

            //voor localhost devapp server
//            LidApi.Builder builder = new LidApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            // einde code voor devappserver


            //app engine appspot (vervang "android-app-backend" met je eigen appspot id)
            LidApi.Builder builder = new LidApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sportbackend2.appspot.com/_ah/api/");
            LidApi api = builder.build();
            ArrayList<Lid> leden = null;
            try {
                leden =  (ArrayList<Lid>) api.listLid().execute().getItems();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return leden;
        }

        protected void onPostExecute(ArrayList<Lid> leden) {
            pDialog.dismiss();
            listView = (ListView) findViewById(R.id.ledenListView);
            LedenLijstAdapter adapter = new LedenLijstAdapter(ListLedenActivity.this, leden);
            listView.setAdapter(adapter);
        }
    }
}
