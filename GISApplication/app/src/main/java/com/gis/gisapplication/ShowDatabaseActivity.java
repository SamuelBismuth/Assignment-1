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

public class ShowDatabaseActivity extends AppCompatActivity {

    TextSwitcher switcherText;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        switcherText = (TextSwitcher) findViewById(R.id.switcher);
        switcherText.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(ShowDatabaseActivity.this);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        switcherText.setInAnimation(this, android.R.anim.fade_in);
        switcherText.setOutAnimation(this, android.R.anim.fade_out);
        onSwitchNext(null);
    }

    public void onSwitchNext(View view) {
        if (count > DataBase.getArraySampleScan().size() - 5)
            count = 0;
        switcherText.setText(
                DataBase.getArraySampleScan().get(count++).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count++).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count++).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count++).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count++).toString()
                );
    }

    public void onSwitchPrev(View view) {
        if (count < 5)
            count = DataBase.getArrayWeightAverage().size();
        switcherText.setText(
                DataBase.getArraySampleScan().get(count--).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count--).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count--).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count--).toString() + "\n" +
                        DataBase.getArraySampleScan().get(count--).toString()
        );
    }

    public void exportFile(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to export !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(ShowDatabaseActivity.this, ExportActivity.class));
        finish();
    }

}
