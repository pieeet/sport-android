package com.rocdev.android.sport;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.rocdev.piet.sport.backend.lidApi.LidApi;
import com.rocdev.piet.sport.backend.lidApi.model.Lid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ListLedenActivity extends AppCompatActivity {
    ListView listView;

    ProgressDialog pDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_leden);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Laden...");
        pDialog.setTitle("Even geduld");
        pDialog.show();

        listView = (ListView) findViewById(R.id.ledenListView);
        verversLedenlijst();


    }


    class LijstOphaler extends AsyncTask<Void, Void, ArrayList<Lid>> {


        @Override
        protected ArrayList<Lid> doInBackground(Void... params) {

            //voor localhost
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
            // end options for devappserver

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



    public void verversLedenlijst() {
        new LijstOphaler().execute();


    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        verversLedenlijst();
//    }



}
