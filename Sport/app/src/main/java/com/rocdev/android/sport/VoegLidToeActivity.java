package com.rocdev.android.sport;

import android.os.AsyncTask;
import android.os.Bundle;
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

/* Benodigde klassen voor localhost server */
//import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
//import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rocdev.piet.sport.backend.lidApi.LidApi;
import com.rocdev.piet.sport.backend.lidApi.model.Lid;

import java.io.IOException;

/**
 * Activity die een lid toevoegt middels een endpoint api. Hiervoor gebruik je
 * de methode insertLid(Lid lid)
 * Je kunt de endpoint api vinden in de backend map (LidEndpoint klasse)
 * Het lid wordt opgeslagen in datastore
 */

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

        //maak referenties naar de invoer widgets
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

    /**
     * voor een netwerk taak is het verplicht een aparte thread te starten.
     * Hiervoor gebruik je de AsyncTask klasse.
     * Bij het maken van de klasse moet je aangeven wat voor type objecten je
     * gaat gebruiken tusen de vishaken.
     * In dit geval wordt er een Lid object aan de thread meegegeven en
     * wordt er een String teruggegeven als de thread is uitgevoerd
     * zie Android cursus hoofdstuk 6
     */
    class LidToevoeger extends AsyncTask<Lid, Void, String> {

        /**
         * achtergrond thread
         * Deze accepteert één, meerdere of een array van Lid objecten als parameter(s), vandaar
         * de puntjes ...
         * In dit geval wordt er een lid object meegegeven
         * In alle gevallen worden de actuele parameters omgezet naar een array,
         * vandaar dat de lid-parameter moet worden opgevraagd met:
         * Lid l = params[0];
         * ook al is er maar één object.
         */

        @Override
        protected String doInBackground(Lid... params) {

            /**
             * instantieer je api klasse, afhankelijk van server waar je op werkt
             * (localhost of appspot.com)
             * als je wilt wisselen kun je betreffende code (uit)commenten
             * Om een api object te maken heb je een builder nodig (zie code)
             */

            //code voor localhost
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



            //code voor app engine appspot (vervang "sportbackend2" met je eigen appspot id)
           LidApi.Builder builder = new LidApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sportbackend2.appspot.com/_ah/api/");

            LidApi api = builder.build();
            Lid lid = params[0];
            try {
                api.insertLid(lid).execute();
                return("Lid toegevoegd");
            } catch (IOException e) {
                return "Er is iets misgegaan";
            }
        }


        /**
         * nadat thread is uitgevoerd kun je iets met het resultaat doen in de main thread
         * het resultaat is het return statement van de doInBackground methode
         */
        protected void onPostExecute(String result) {
            Toast.makeText(VoegLidToeActivity.this.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
