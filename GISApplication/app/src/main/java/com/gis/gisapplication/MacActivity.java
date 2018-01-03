package com.gis.gisapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import libraries.DataBase;
import objects.Mac;
import objects.MacInformation;
import objects.MacInformationAlgo1;
import objects.SampleScan;
import objects.WeigthAverage;
import objects.Wifi;
import runs.CallableAlgorithm2;

public class MacActivity extends AppCompatActivity {

    private EditText macAlgo1, latitude, longitude, altitude,
            mac1Algo2, mac2Algo2, mac3Algo2, signal1, signal2, signal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac);
        macAlgo1 = (EditText) findViewById(R.id.macAlgo1);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        altitude = (EditText) findViewById(R.id.altitude);
        mac1Algo2 = (EditText) findViewById(R.id.mac1Algo2);
        mac2Algo2 = (EditText) findViewById(R.id.mac1Algo2);
        mac3Algo2 = (EditText) findViewById(R.id.mac3Algo3);
        signal1 = (EditText) findViewById(R.id.signal1);
        signal2 = (EditText) findViewById(R.id.signal2);
        signal3 = (EditText) findViewById(R.id.signal3);
    }

    /**
     * TODO : REFRESH DATA BASE ALL THE CHANGEMENT : SAMPLE SCAN, MAC, CSFILE
     * @param view
     */
    public void algo1(View view) {
        if (macAlgo1.getText().toString().equals("") || latitude.getText().toString().equals("")
                || longitude.getText().toString().equals("") || altitude.getText().toString()       .equals("") ) {
            Toast.makeText(this, "Please fulfill all the fields !", Toast.LENGTH_SHORT).show();
            return;
        }
        Mac mac = new Mac(
                macAlgo1.getText().toString(),
                newArrayMacInfomation()
        );
        for (Mac macHelper : DataBase.getArrayMac())
            if (macHelper.equals(mac))
                mac.getArrayMacLocation().addAll(macHelper.getArrayMacLocation());
        Toast.makeText(this, mac.getWeightCenter().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

    private ArrayList<MacInformation> newArrayMacInfomation() {
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
        array.add(new MacInformationAlgo1(
                    new EarthCoordinate(
                            Double.parseDouble(latitude.getText().toString()),
                            Double.parseDouble(longitude.getText().toString()),
                            Double.parseDouble(altitude.getText().toString())
                            ),
                    null
                   )
        );
        return array;
    }

    public void algo2(View view) {
        if (mac1Algo2.getText().toString().equals("") || mac2Algo2.getText().toString().equals("") || mac3Algo2.getText().toString().equals("")
                || signal1.getText().toString().equals("") || signal2.getText().toString().equals("") || signal3.getText().toString().equals("") ) {
            Toast.makeText(this, "Please fulfill all the fields !", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<SampleScan> input = new ArrayList<>();
        ArrayList<Wifi> arrayWifi = new ArrayList<>();
        arrayWifi.add(new Wifi(
                mac1Algo2.getText().toString(),
                Double.parseDouble(signal1.getText().toString())
        ));
        arrayWifi.add(new Wifi(
                mac2Algo2.getText().toString(),
                Double.parseDouble(signal2.getText().toString())
        ));
        arrayWifi.add(new Wifi(
                mac3Algo2.getText().toString(),
                Double.parseDouble(signal3.getText().toString())
        ));
        input.add(
                new SampleScan(
                    new EarthCoordinate(
                            0.0,
                            0.0,
                            0.0
                    ),
                        arrayWifi
        ));
        ArrayList<WeigthAverage> arrayData = new ArrayList<WeigthAverage>();
        for (SampleScan data : DataBase.getArraySampleScan())
            arrayData.add(new WeigthAverage(data));
        DataBase.setArrayWeightAverage(arrayData);
        ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
        Future<ArrayList<SampleScan>> future = execut.submit(new CallableAlgorithm2(input));
        while (!future.isDone());
        try {
            input = future.get();
        }
        catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        Toast.makeText(this, input.get(0).getPointLocation().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

}
