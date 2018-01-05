package com.gis.gisapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import objects.Filter;
import libraries.DataBase;

public class ShowFilterActivity extends AppCompatActivity {

    private int count = 0;
    private TextView filterShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_filter);
        filterShow = (TextView) findViewById(R.id.showFilter);
        filterShow.setText(setFilterDatabase());
    }

    private String setFilterDatabase() {
        String str = null;
        for (Filter filter : DataBase.getFilterStack()) {
            str += filter.toString();
            str += "\n";
        }
        return str;
    }

    public void undo(View view) {
        Filter filter = DataBase.popStack();
        DataBase.setArraySampleScan(filter.getArray());
        MainActivity.refreshDataBase();
        finish();
    }

}
