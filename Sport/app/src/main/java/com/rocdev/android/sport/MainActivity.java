package com.rocdev.android.sport;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    }
}
