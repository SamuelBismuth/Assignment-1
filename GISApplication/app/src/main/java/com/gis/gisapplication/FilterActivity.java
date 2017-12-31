package com.gis.gisapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class FilterActivity extends AppCompatActivity {

    Switch time, place, id;
    RadioButton and, or, not, none;
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        time = (Switch) findViewById(R.id.time);
        place = (Switch) findViewById(R.id.place);
        id = (Switch) findViewById(R.id.id);
        and = (RadioButton) findViewById(R.id.and);
        or = (RadioButton) findViewById(R.id.or);
        not = (RadioButton) findViewById(R.id.not);
        none = (RadioButton) findViewById(R.id.none);
        next = (Button) findViewById(R.id.next);
    }

    public void next(View view) {
        if (none.isChecked() || not.isChecked())
            checkTheFilterChoice();
        else
            checkTheMultipleFilterChoice();
    }

    private void checkTheFilterChoice() {
        if (time.isChecked()) {
            startActivity(new Intent(FilterActivity.this, TimeActivity.class));
            finish();
            return;
        }
        if (place.isChecked()) {
            startActivity(new Intent(FilterActivity.this, PlaceActivity.class));
            finish();
            return;
        }
        if (id.isChecked()) {
            startActivity(new Intent(FilterActivity.this, IdActivity.class));
            finish();
            return;
        }
    }

    private void checkTheMultipleFilterChoice() {
        if (time.isChecked() && place.isChecked()) {
            startActivity(new Intent(FilterActivity.this, TimeActivity.class));
            startActivity(new Intent(FilterActivity.this, PlaceActivity.class));
            return;
        }
        if (place.isChecked() && id.isChecked()) {
            startActivity(new Intent(FilterActivity.this, PlaceActivity.class));
            startActivity(new Intent(FilterActivity.this, IdActivity.class));
            return;
        }
        if (id.isChecked() && time.isChecked()) {
            startActivity(new Intent(FilterActivity.this, IdActivity.class));
            startActivity(new Intent(FilterActivity.this, TimeActivity.class));
            return;
        }
    }

}
