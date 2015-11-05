package com.rocdev.android.sport;

import android.content.Intent;
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
import com.rocdev.piet.sport.backend.lidApi.LidApi;
import com.rocdev.piet.sport.backend.lidApi.model.Lid;


import java.io.IOException;

public class WijzigLidActivity extends AppCompatActivity implements View.OnClickListener{

    EditText roepnaamEditText;
    EditText tussenvoegelsEditText;
    EditText achternaamEditText;
    EditText adresEditText;
    EditText postcodeEditText;
    EditText woonplaatsEditText;
    EditText telefoonEditText;
    EditText emailEditText;
    EditText geboortedatumEditText;
    Button wijzigLidButton;
    Spinner geboortedatumSpinner;
    ArrayAdapter<CharSequence> adapter;
    Lid lid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wijzig_lid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        lid = new Lid();
        lid.setSpelerscode(intent.getStringExtra("spelerscode"));

        String roepnaam = intent.getStringExtra("roepnaam");
        lid.setRoepnaam(roepnaam);
        roepnaamEditText = (EditText) findViewById(R.id.wijzigRoepnaamEditText);
        roepnaamEditText.setText(roepnaam);

        String tussenvoegsels = intent.getStringExtra("tussenvoegsels");
        lid.setTussenvoegsels(tussenvoegsels);
        tussenvoegelsEditText = (EditText) findViewById(R.id.wijzigTussenvoegselsEditText);
        tussenvoegelsEditText.setText(tussenvoegsels);

        String achternaam = intent.getStringExtra("achternaam");
        lid.setAchternaam(achternaam);
        achternaamEditText = (EditText) findViewById(R.id.wijzigAchternaamEditText);
        achternaamEditText.setText(achternaam);

        String adres = intent.getStringExtra("adres");
        lid.setAdres(adres);
        adresEditText = (EditText) findViewById(R.id.wijzigAdresEditText);
        adresEditText.setText(adres);

        String postcode = intent.getStringExtra("postcode");
        lid.setPostcode(postcode);
        postcodeEditText = (EditText) findViewById(R.id.wijzigPostcodeEditText);
        postcodeEditText.setText(postcode);

        String woonplaats = intent.getStringExtra("woonplaats");
        lid.setWoonplaats(woonplaats);
        woonplaatsEditText = (EditText) findViewById(R.id.wijzigWoonplaatsEditText);
        woonplaatsEditText.setText(woonplaats);

        String telefoon = intent.getStringExtra("telefoon");
        lid.setTelefoon(telefoon);
        telefoonEditText = (EditText) findViewById(R.id.wijzigTelefoonEditText);
        telefoonEditText.setText(telefoon);

        String email = intent.getStringExtra("email");
        lid.setEmail(email);
        emailEditText = (EditText) findViewById(R.id.wijzigEmailEditText);
        emailEditText.setText(email);

        String geboortedatum = intent.getStringExtra("geboortedatum");
        lid.setGeboortedatum(geboortedatum);
        geboortedatumEditText = (EditText) findViewById(R.id.wijzigGeboortedatumEditText);
        geboortedatumEditText.setText(geboortedatum);

        String geslacht = intent.getStringExtra("geslacht");
        lid.setGeslacht(geslacht);
        geboortedatumSpinner = (Spinner) findViewById(R.id.wijzigGeslachtSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.geslachtspinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        geboortedatumSpinner.setAdapter(adapter);
        if (geslacht.equalsIgnoreCase("vrouw")) {
            geboortedatumSpinner.setSelection(adapter.getPosition("vrouw"));
        }
        wijzigLidButton = (Button) findViewById((R.id.wijzigLidButton));
        wijzigLidButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        lid.setRoepnaam(roepnaamEditText.getText().toString());
        lid.setTussenvoegsels(tussenvoegelsEditText.getText().toString());
        lid.setAchternaam(achternaamEditText.getText().toString());
        lid.setAdres(adresEditText.getText().toString());
        lid.setPostcode(postcodeEditText.getText().toString());
        lid.setWoonplaats(woonplaatsEditText.getText().toString());
        lid.setGeboortedatum(geboortedatumEditText.getText().toString());
        lid.setTelefoon(telefoonEditText.getText().toString());
        lid.setGeslacht(geboortedatumSpinner.getSelectedItem().toString());
        lid.setEmail(emailEditText.getText().toString());
        new LidWijziger().execute(lid);
    }

    class LidWijziger extends AsyncTask<Lid, Void, String> {

        @Override
        protected String doInBackground(Lid... params) {
            Lid lid = params[0];
            LidApi.Builder builder = new LidApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sportbackend2.appspot.com/_ah/api/");

            LidApi api = builder.build();
            try {
                lid = api.insertLid(lid).execute();
                return("Lid gewijzigd");
            } catch (IOException e) {
                e.printStackTrace();
                return("Er is iets misgegaan");

            }
        }

        protected void onPostExecute(String result) {
            Toast.makeText(WijzigLidActivity.this.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }

}
