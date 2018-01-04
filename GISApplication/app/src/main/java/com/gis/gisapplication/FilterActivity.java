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
import android.widget.Toast;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import objects.Filter;
import filter.Filtering;
import filter.FilteringKmlId;
import filter.FilteringKmlPlace;
import filter.FilteringKmlTime;
import libraries.DataBase;
import objects.Logic;
import objects.SampleScan;

/**
 * This class represents the filter activity.
 * This class is an gui which allow the user to choose by the efficiency way the filter he wants to custom.
 *
 * @author Orel and Samuel.
 */
public class FilterActivity extends AppCompatActivity {

    private Logic logic = new Logic("None");

    private Filtering filtering1, filtering2;

    private static int yearBeginning, monthBeginning, dayBeginning, hoursBeginning, minBeginning,
            yearEnd, monthEnd, dayEnd, hoursEnd, minEnd,
            yearBeginningFilter2, monthBeginningFilter2, dayBeginningFilter2, hoursBeginningFilter2, minBeginningFilter2,
            yearEndFilter2, monthEndFilter2, dayEndFilter2, hoursEndFilter2, minEndFilter2;

    private String choiceFilter1 = "Time", choiceFilter2;

    private Spinner spinnerLogic, spinnerFilter1, spinnerFilter2;

    private EditText editTextIdFilter1, latitudePlaceFilter1, longitudePlaceFilter1, radiusPlaceFilter1,
            editTextIdFilter2, latitudePlaceFilter2, longitudePlaceFilter2, radiusPlaceFilter2;

    private static TextView displayTimeBeginning, displayDateBeginning, displayTimeEnd, displayDateEnd,
            displayTimeBeginningFilter2, displayDateBeginningFilter2, displayTimeEndFilter2, displayDateEndFilter2;

    private LinearLayout filter2, idFilter1, placeFilter1, timeFilter1, idFilter2, placeFilter2, timeFilter2;

    private Switch not;

    /**
     * This is the onCreate method/constructor.
     * Here are define all the "findViewById" to recuperate the data given by the user.
     *
     * On this class, there is a lot of reflexion about when a given functionality should appears or not.
     * In another words, all the useless functionality are hidden.
     *
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

        filter2 = (LinearLayout) findViewById(R.id.filter2);
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

        /**
         * This function create a selected listener for the choice of the logic.
         */
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

        /**
         * This function create a selected listener for the first filter.
         */
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
        /**
         * This function create a selected listener for the choice of the second filter.
         */
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

    /**
     * This method create a time picker.
     */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        /**
         * This function recuperate the time given by the user.
         *
         * @param view
         * @param hourOfDay
         * @param minute
         */
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setTimeFilter(this.getTag().toString(), hourOfDay, minute);
        }
    }

    /**
     * This method create a time picker.
     */
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

        /**
         * This function recuperate the date given by the user.
         *
         * @param view
         * @param year
         * @param month
         * @param day
         */
        public void onDateSet(DatePicker view, int year, int month, int day) {
            setDateFilter(this.getTag().toString(), year, month, day);
        }
    }

    /**
     * This function shows the time picker for the time of the beginning for the first filter.
     *
     * @param v
     */
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    /**
     * This method shows the date picker for the date of the beginning for the first filter.
     *
     * @param v
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    /**
     * This function shows the time picker for the time of the end for the first filter.
     *
     * @param v
     */
    public void showTimePickerDialogEnd(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerEnd");
    }

    /**
     * This method shows the date picker for the date of the end for the first filter.
     *
     * @param v
     */
    public void showDatePickerDialogEnd(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerEnd");
    }

    /**
     * This function shows the time picker for the time of the beginning for the second filter.
     *
     * @param v
     */
    public void showTimePickerDialogFilter2(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerFilter2");
    }

    /**
     * This method shows the date picker for the date of the beginning for the second filter.
     *
     * @param v
     */
    public void showDatePickerDialogFilter2(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerFilter2");
    }

    /**
     * This function shows the time picker for the time of the end for the second filter.
     *
     * @param v
     */
    public void showTimePickerDialogEndFilter2(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerEndFilter2");
    }

    /**
     * This method shows the date picker for the date of the end for the second filter.
     *
     * @param v
     */
    public void showDatePickerDialogEndFilter2(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerEndFilter2");
    }

    /**
     * This function is call if the logic is on none, that's mean, when the user do not wants to use any logic.
     */
    public void onNone() {
        logic.setLogic("None");
        filter2.setVisibility(View.GONE);
        placeFilter2.setVisibility(View.GONE);
        idFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.GONE);
    }

    /**
     * This function is call if the logic is on and, that's mean, when the user wants to use the logic and.
     * Example : {1, 2} and {2, 3} = {2}. Another word is intersection.
     */
    public void onAnd() {
        logic.setLogic("And");
        filter2.setVisibility(View.VISIBLE);
        timeFilter2.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the logic is on or, that's mean, when the user wants to use the logic or.
     * Example : {1, 2} and {2, 3} = {1, 2, 3}. Another word is union.
     */
    public void onOr() {
        logic.setLogic("Or");
        filter2.setVisibility(View.VISIBLE);
        timeFilter2.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the user choose the first filter time.
     */
    public void onTime() {
        choiceFilter1 = "Time";
        placeFilter1.setVisibility(View.GONE);
        idFilter1.setVisibility(View.GONE);
        timeFilter1.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the user choose the first filter id.
     */
    public void onId() {
        choiceFilter1 = "Id";
        placeFilter1.setVisibility(View.GONE);
        timeFilter1.setVisibility(View.GONE);
        idFilter1.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the user choose the first filter place.
     */
    public void onPlace() {
        choiceFilter1 = "Place";
        idFilter1.setVisibility(View.GONE);
        timeFilter1.setVisibility(View.GONE);
        placeFilter1.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the user choose the second filter time.
     */
    public void onTimeFilter2() {
        placeFilter2.setVisibility(View.GONE);
        idFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the user choose the second filter id.
     */
    public void onIdFilter2() {
        placeFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.GONE);
        idFilter2.setVisibility(View.VISIBLE);
    }

    /**
     * This function is call if the user choose the second filter place.
     */
    public void onPlaceFilter2() {
        idFilter2.setVisibility(View.GONE);
        timeFilter2.setVisibility(View.GONE);
        placeFilter2.setVisibility(View.VISIBLE);
    }

    /**
     * This function set the time choose by the user.
     *
     * @param tag
     * @param hour
     * @param minute
     */
    public static void setTimeFilter(String tag, int hour, int minute) {
        switch (tag) {
            case "timePicker":
                hoursBeginning = hour;
                minBeginning = minute;
                displayTimeBeginning.setText("You picked : Hour : " + Integer.toString(hour) + " Minute : " + Integer.toString(minute));
                break;
            case "timePickerEnd":
                hoursEnd = hour;
                minEnd = minute;
                displayTimeEnd.setText("You picked : Hour : " + Integer.toString(hour) + " Minute : " + Integer.toString(minute));
                break;
            case "timePickerFilter2":
                hoursBeginningFilter2 = hour;
                minBeginningFilter2 = minute;
                displayTimeBeginningFilter2.setText("You picked : Hour : " + Integer.toString(hour) + " Minute : " + Integer.toString(minute));
                break;
            case "timePickerEndFilter2":
                hoursEndFilter2 = hour;
                minEndFilter2 = minute;
                displayTimeEndFilter2.setText("You picked : Hour : " + Integer.toString(hour) + " Minute : " + Integer.toString(minute));
                break;
            default:
        }
    }

    /**
     * This function set the date choose by the user.
     *
     * @param tag
     * @param year
     * @param month
     * @param day
     */
    public static void setDateFilter(String tag, int year, int month, int day) {
        switch (tag) {
            case "datePicker":
                yearBeginning = year;
                monthBeginning = month;
                dayBeginning = day;
                displayDateBeginning.setText("You picked : Year :" + Integer.toString(year) + " Month :" + Integer.toString(month) + " Day :" + Integer.toString(day));
                break;
            case "datePickerEnd":
                yearEnd = year;
                monthEnd = month;
                dayEnd = day;
                displayDateEnd.setText("You picked : Year :" + Integer.toString(year) + " Month :" + Integer.toString(month) + " Day :" + Integer.toString(day));
                break;
            case "datePickerFilter2":
                yearEndFilter2 = year;
                monthEndFilter2 = month;
                dayEndFilter2 = day;
                displayDateBeginningFilter2.setText("You picked : Year :" + Integer.toString(year) + " Month :" + Integer.toString(month) + " Day :" + Integer.toString(day));
                break;
            case "datePickerEndFilter2":
                yearBeginningFilter2 = year;
                monthBeginningFilter2 = month;
                dayBeginningFilter2 = day;
                displayDateEndFilter2.setText("You picked : Year :" + Integer.toString(year) + " Month :" + Integer.toString(month) + " Day :" + Integer.toString(day));
                break;
            default:
        }
    }

    /**
     * This function is call when the user wants to execute the created filter.
     * There is no need here to control the user choice because, the filter can be custom like the user wants.
     * Then, we finish the activity.
     *
     * @param view
     */
    public void filter(View view) {
        Filter filter = new Filter(
                (ArrayList<SampleScan>) DataBase.getArraySampleScan().clone(),
                logic,
                not.isChecked(),
                createFilter1(filtering1),
                createFilter2(filtering2)
        );
        filter.run();
        DataBase.pushFilter(filter);
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * This method get all the data given by the user and use it to create the object filter.
     * This is for the filter 1. If the user choose the and or or logic, a filter 2 is also asked.
     *
     * @param filtering
     * @return Filtering.
     */
    private Filtering createFilter1(Filtering filtering) {
        switch (choiceFilter1) {
            case "Id":
                return new FilteringKmlId(editTextIdFilter1.getText().toString());
            case "Place":
                return new FilteringKmlPlace(
                        new EarthCoordinate(
                                Double.parseDouble(latitudePlaceFilter1.getText().toString()),
                                Double.parseDouble(longitudePlaceFilter1.getText().toString()),
                                0.0
                        ),
                        Double.parseDouble(radiusPlaceFilter1.getText().toString())
                );
            case "Time":
                return new FilteringKmlTime(
                        new GregorianCalendar(
                                yearBeginning,
                                monthBeginning,
                                dayBeginning,
                                hoursBeginning,
                                minBeginning
                        ),
                        new GregorianCalendar(
                                yearEnd,
                                monthEnd,
                                dayEnd,
                                hoursEnd,
                                minEnd
                        )
                );

            default:
                return null;
        }
    }

    /**
     * This method get all the data given by the user and use it to create the second object filter.
     *
     * @param filtering
     * @return filtering
     */
    private Filtering createFilter2(Filtering filtering) {
        if (choiceFilter1 == null || choiceFilter2 == null)
            return null;
        switch (choiceFilter2) {
            case "Id":
                return new FilteringKmlId(editTextIdFilter2.getText().toString());
            case "Place":
                return new FilteringKmlPlace(
                        new EarthCoordinate(
                                Double.parseDouble(latitudePlaceFilter2.getText().toString()),
                                Double.parseDouble(longitudePlaceFilter2.getText().toString()),
                                0.0
                        ),
                        Double.parseDouble(radiusPlaceFilter2.getText().toString())
                );
            case "Time":
                return new FilteringKmlTime(
                        new GregorianCalendar(
                                yearBeginningFilter2,
                                monthBeginningFilter2,
                                dayBeginningFilter2,
                                hoursBeginningFilter2,
                                minBeginningFilter2
                        ),
                        new GregorianCalendar(
                                yearEndFilter2,
                                monthEndFilter2,
                                dayEndFilter2,
                                hoursEndFilter2,
                                minEndFilter2
                        )
                );

            default:
                return null;
        }
    }

}
