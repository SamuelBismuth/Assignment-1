package com.gis.gisapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import filter.Filter;
import libraries.DataBase;

public class ShowFilterActivity extends AppCompatActivity {

    TextSwitcher switcherText;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_filter);
        switcherText = (TextSwitcher) findViewById(R.id.switcher);
        switcherText.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(ShowFilterActivity.this);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        switcherText.setInAnimation(this, android.R.anim.fade_in);
        switcherText.setOutAnimation(this, android.R.anim.fade_out);
        onSwitchNext(null);
    }

    public void onSwitchNext(View view) {
        if (count == DataBase.getFilterStack().size())
            count = 0;
        switcherText.setText(
                DataBase.getFilterStack().get(count++).toString() + "\n"
        );
    }

    public void onSwitchPrev(View view) {
        if (DataBase.getFilterStack().size() == 1)
            return;
        if (count == 0)
            count = DataBase.getFilterStack().size() - 1;
        switcherText.setText(
                DataBase.getFilterStack().get(count--).toString() + "\n"
        );
    }

    public void undo (View view) {
        Filter filter = DataBase.popStack();
        DataBase.setArraySampleScan(filter.getArray());
        MainActivity.refreshDataBase();
        finish();
    }
}
