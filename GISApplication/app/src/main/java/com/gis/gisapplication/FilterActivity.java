package com.gis.gisapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class FilterActivity extends AppCompatActivity {

    private Spinner spinnerLogic, spinnerFilter1, spinnerFilter2;
    private EditText editTextIdFilter1, latitudePlaceFilter1, longitudePlaceFilter1, radiusPlaceFilter1,
            editTextIdFilter2, latitudePlaceFilter2, longitudePlaceFilter2, radiusPlaceFilter2;
    private static TextView displayTimeBeginning, displayDateBeginning,displayTimeEnd, displayDateEnd,
            displayTimeBeginningFilter2, displayDateBeginningFilter2, displayTimeEndFilter2, displayDateEndFilter2;
    private LinearLayout filter2, idFilter1, placeFilter1, timeFilter1, idFilter2, placeFilter2, timeFilter2;
    private Switch not;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spinnerLogic = (Spinner) findViewById(R.id.spinnerLogic);
        spinnerFilter1 = (Spinner) findViewById(R.id.spinnerFilter1);
        spinnerFilter2 = (Spinner) findViewById(R.id.spinnerFilter2);

        not = (Switch) findViewById(R.id.not);

        filter2 =  (LinearLayout) findViewById(R.id.filter2);
        idFilter1 = (LinearLayout) findViewById(R.id.idFilter1);
        placeFilter1 = (LinearLayout) findViewById(R.id.placeFilter1);
        timeFilter1 = (LinearLayout) findViewById(R.id.timeFilter1);
        idFilter2 = (LinearLayout) findViewById(R.id.idFilter2);
        placeFilter2 = (LinearLayout) findViewById(R.id.placeFilter2);
        timeFilter2 = (LinearLayout) findViewById(R.id.timeFilter2);

        editTextIdFilter1 = (EditText) findViewById(R.id.editTextIdFilter2);

        editTextIdFilter2 = (EditText) findViewById(R.id.editTextIdFilter1);

        latitudePlaceFilter1 = (EditText) findViewById(R.id.latitudePlaceFilter1);
        longitudePlaceFilter1 = (EditText) findViewById(R.id.longitudePlaceFilter1);
        radiusPlaceFilter1 = (EditText) findViewById(R.id.radiusPlaceFilter1);

        latitudePlaceFilter2 = (EditText) findViewById(R.id.latitudePlaceFilter2);
        longitudePlaceFilter2 = (EditText) findViewById(R.id.longitudePlaceFilter2);
        radiusPlaceFilter2 = (EditText) findViewById(R.id.radiusPlaceFilter2);

        displayTimeBeginning = (TextView) findViewById(R.id.displayTimeBeginning);
        displayDateBeginning = (TextView) findViewById(R.id.displayDateBeginning);
        displayTimeEnd = (TextView) findViewById(R.id.displayTimeEnd);
        displayDateEnd = (TextView) findViewById(R.id.displayDateEnd);

        displayTimeBeginningFilter2 = (TextView) findViewById(R.id.displayTimeBeginningFilter2);
        displayDateBeginningFilter2 = (TextView) findViewById(R.id.displayDateBeginningFilter2);
        displayTimeEndFilter2 = (TextView) findViewById(R.id.displayTimeEndFilter2);
        displayDateEndFilter2 = (TextView) findViewById(R.id.displayDateEndFilter2);

        spinnerLogic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "And":
                        onAnd();
                        break;
                    case "Or":
                        onOr();
                        break;
                    default:
                        onNone();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFilter1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Time":
                        onTime();
                        break;
                    case "ID":
                        onId();
                        break;
                    case "Place":
                        onPlace();
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFilter2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Time":
                        onTimeFilter2();
                        break;
                    case "ID":
                        onIdFilter2();
                        break;
                    case "Place":
                        onPlaceFilter2();
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setTimeFilter(this.getTag().toString(), hourOfDay, minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            setDateFilter(this.getTag().toString(), year, month, day);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialogEnd(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerEnd");
    }

    public void showDatePickerDialogEnd(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerEnd");
    }

    public void showTimePickerDialogFilter2(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerFilter2");
    }

    public void showDatePickerDialogFilter2(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerFilter2");
    }

    public void showTimePickerDialogEndFilter2(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerEndFilter2");
    }

    public void showDatePickerDialogEndFilter2(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerEndFilter2");
    }

    public void onNone() {
        filter2.setVisibility(View.GONE);
        placeFilter2.setVisibility(View.GONE);
        idFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.GONE);
    }

    public void onAnd() {
        filter2.setVisibility(View.VISIBLE);
        timeFilter2.setVisibility(View.VISIBLE);
    }

    public void onOr() {
        filter2.setVisibility(View.VISIBLE);
        timeFilter2.setVisibility(View.VISIBLE);
    }

    public void onTime() {
        placeFilter1.setVisibility(View.GONE);
        idFilter1.setVisibility(View.GONE);
        timeFilter1.setVisibility(View.VISIBLE);
    }

    public void onId() {
        placeFilter1.setVisibility(View.GONE);
        timeFilter1.setVisibility(View.GONE);
        idFilter1.setVisibility(View.VISIBLE);
    }

    public void onPlace() {
        idFilter1.setVisibility(View.GONE);
        timeFilter1.setVisibility(View.GONE);
        placeFilter1.setVisibility(View.VISIBLE);
    }

    public void onTimeFilter2() {
        placeFilter2.setVisibility(View.GONE);
        idFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.VISIBLE);
    }

    public void onIdFilter2() {
        placeFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.GONE);
        idFilter2.setVisibility(View.VISIBLE);
    }

    public void onPlaceFilter2() {
        idFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.GONE);
        placeFilter2.setVisibility(View.VISIBLE);
    }

    public static void setTimeFilter(String tag, int hour, int minute){
        switch (tag) {
            case "timePicker" :
                displayTimeBeginning.setText("You picked : Hour :" + Integer.toString(hour) + "Minute :" + Integer.toString(minute));
                break;
            case "timePickerEnd" :
                displayTimeEnd.setText("You picked : Hour :" + Integer.toString(hour) + "Minute :" + Integer.toString(minute));
                break;
            case "timePickerFilter2" :
                displayTimeBeginningFilter2.setText("You picked : Hour :" + Integer.toString(hour) + "Minute :" + Integer.toString(minute));
                break;
            case "timePickerEndFilter2" :
                displayTimeEndFilter2.setText("You picked : Hour :" + Integer.toString(hour) + "Minute :" + Integer.toString(minute));
                break;
            default:
        }
    }

    public static void setDateFilter(String tag, int year, int month, int day) {
        switch (tag) {
            case "datePicker":
                displayDateBeginning.setText("You picked : Year :" + Integer.toString(year) + "Month :" + Integer.toString(month) + "Day :" + Integer.toString(day));
                 break;
            case "datePickerEnd" :
                displayDateEnd.setText("You picked : Year :" + Integer.toString(year) + "Month :" + Integer.toString(month) + "Day :" + Integer.toString(day));
                break;
            case "datePickerFilter2":
                displayDateBeginningFilter2.setText("You picked : Year :" + Integer.toString(year) + "Month :" + Integer.toString(month) + "Day :" + Integer.toString(day));
                break;
            case "datePickerEndFilter2" :
                displayDateEndFilter2.setText("You picked : Year :" + Integer.toString(year) + "Month :" + Integer.toString(month) + "Day :" + Integer.toString(day));
                break;
            default:
        }
    }

    public void filter(View view) {
        //logical
        //not
        //filter1
        //filter2?
    }

}
