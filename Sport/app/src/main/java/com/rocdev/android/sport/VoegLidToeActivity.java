package com.rocdev.android.sport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rocdev.piet.sport.backend.lidApi.LidApi;
import com.rocdev.piet.sport.backend.lidApi.model.Lid;

import java.io.IOException;

public class VoegLidToeActivity extends AppCompatActivity implements View.OnClickListener{
    EditText roepnaamEditText;
    EditText tussenvoegelsEditText;
    EditText achternaamEditText;
    EditText adresEditText;
    EditText postcodeEditText;
    EditText woonplaatsEditText;
    EditText telefoonEditText;
    EditText emailEditText;
    EditText geboortedatumEditText;
    Button verzendNieuwLidButton;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voeg_lid_toe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        roepnaamEditText = (EditText) findViewById(R.id.roepnaamEditText);
        tussenvoegelsEditText = (EditText) findViewById(R.id.tussenvoegselsEditText);
        achternaamEditText = (EditText) findViewById(R.id.achternaamEditText);
        adresEditText = (EditText) findViewById(R.id.adresEditText);
        postcodeEditText = (EditText) findViewById(R.id.postcodeEditText);
        woonplaatsEditText = (EditText) findViewById(R.id.woonplaatsEditText);
        telefoonEditText = (EditText) findViewById(R.id.telefoonEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        geboortedatumEditText = (EditText) findViewById(R.id.geboortedatumEditText);
        verzendNieuwLidButton = (Button) findViewById((R.id.verzendLidButton));
        verzendNieuwLidButton.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.geslachtSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.geslachtspinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        String roepnaam = roepnaamEditText.getText().toString();
        String tussenvoegsels = tussenvoegelsEditText.getText().toString();
        String achternaam = achternaamEditText.getText().toString();
        String adres = adresEditText.getText().toString();
        String postcode = postcodeEditText.getText().toString();
        String woonplaats = woonplaatsEditText.getText().toString();
        String telefoon = telefoonEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String geboortedatum = geboortedatumEditText.getText().toString();
        String geslacht = spinner.getSelectedItem().toString();

        Lid lid = new Lid();
        lid.setSpelerscode(email);
        lid.setRoepnaam(roepnaam);
        lid.setTussenvoegsels(tussenvoegsels);
        lid.setAchternaam(achternaam);
        lid.setAdres(adres);
        lid.setPostcode(postcode);
        lid.setEmail(email);
        lid.setWoonplaats(woonplaats);
        lid.setTelefoon(telefoon);
        lid.setGeslacht(geslacht);
        lid.setGeboortedatum(geboortedatum);
        new LidToevoeger().execute(lid);

    }

    class LidToevoeger extends AsyncTask<Lid, Void, String> {
        private LidApi api = null;

        @Override
        protected String doInBackground(Lid... params) {

            //localhost
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



            api = builder.build();
            Lid l = params[0];
            try {
                api.insertLid(l).execute();
                return("Lid toegevoegd");
            } catch (IOException e) {
                return "Er is iets misgegaan";
            }
        }

        protected void onPostExecute(String result) {
            Toast.makeText(VoegLidToeActivity.this.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
