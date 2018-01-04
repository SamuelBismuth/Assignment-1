package com.gis.gisapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import libraries.DataBase;
import objects.SampleScan;

/**
 * This class show the "SHOW DATABASE" activity.
 *
 * @author Orel and Samuel.
 */
public class ShowDatabaseActivity extends AppCompatActivity {

    TextSwitcher switcherText;
    TextView textView;
    private int count = 0;

    /**
     * This is the onCreate method/constructor.
     * Here are define all the "findViewById" to recuperate the data given by the user.
     * This constructor is activate once and only once, when the user open the activity for the first time.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        textView = (TextView) findViewById(R.id.text);
        textView.setText(
               setDatabaseText()
        );
    }

    public String setDatabaseText() {
        String str = null;
        for(SampleScan sampleScan : DataBase.getArraySampleScan()) {
            str += sampleScan.toString();
            str += "\n";
        }
        return str;
    }

    /**
     * This function export the file.
     *
     * @param view
     */
    public void exportFile(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to export !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(ShowDatabaseActivity.this, ExportActivity.class));
        finish();
    }

}
