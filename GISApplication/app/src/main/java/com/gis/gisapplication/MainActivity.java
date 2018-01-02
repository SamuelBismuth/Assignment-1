package com.gis.gisapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import cast.Cast;
import cast.CastFromCsvFileToSampleScan;
import cast.CastFromMacToLineAlgo1;
import cast.CastFromSampleScanToMac;
import libraries.DataBase;
import objects.CsvFile;
import objects.LineAlgo1;
import objects.Mac;
import objects.SampleScan;
import read.ReadCombo;
import read.ReadCsv;
import read.ReadWigleWifi;
import runs.CallableCast;
import runs.RunRead;
import runs.RunWrite;
import write.WriteCombo;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * TODO : filter
 * TODO : DISPLAY ALL THE FILTER (STACK)
 * TODO : If import new file, possibility to access to all the filter we did.
 * TODO : UNDO
 * TODO : read folder.
 * TODO : ALGO 1 ET 2
 * TODO : reorganize the functions and java doc et tout le bordel
 * TODO : SEE IF THREAD IN A GOOD PLACE (always need threads ?) (answer sephie)
 * TODO : REORGANIZE ALL THE THREAD (CLAABLE NEED TO BE IMPROVE : USE OF THE DATRA BASE)
 * TODO : DESIGN !!
 */
public class MainActivity extends AppCompatActivity {

    private Button importFile;
    private TextView numberOfSampleScan, numberOfWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }

        importFile = (Button) findViewById(R.id.importFile);
        numberOfSampleScan = (TextView) findViewById(R.id.numberOfSampleScan);
        numberOfSampleScan.setText("0");
        numberOfWifi = (TextView) findViewById(R.id.numberOfWifi);
        numberOfWifi.setText("0");

        importFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                    .withActivity(MainActivity.this)
                    .withRequestCode(1000)
                    .withFilter(Pattern.compile(".*\\.csv$")) // Filtering files and directories by file name using regexp
                    .withHiddenFiles(true) // Show hidden files and folders
                    .start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            ArrayList<SampleScan> arraySampleScan =  new ArrayList<SampleScan>();
            ArrayList<CsvFile> arrayCsvFile = new ArrayList<CsvFile>();
            Thread threadRead;
            if (read(filePath)) {
                ReadCsv<CsvFile> readWigleWifi = new ReadWigleWifi(filePath, arrayCsvFile);
                threadRead = new Thread(new RunRead<CsvFile>(readWigleWifi, filePath));
            }
            else {
                ReadCsv<SampleScan> readCombo = new ReadCombo(filePath, arraySampleScan, this);
                threadRead = new Thread(new RunRead<SampleScan>(readCombo, filePath));
            }
            threadRead.start();
            try {
                threadRead.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (arrayCsvFile.size() != 0)  {
                DataBase.addArrayCsvFile(arrayCsvFile);
                Cast castCsv = new CastFromCsvFileToSampleScan();
                ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
                Future<ArrayList<SampleScan>> future = execut.submit(new CallableCast<CsvFile, SampleScan>(castCsv, arrayCsvFile));
                while (!future.isDone());
                try {
                    arraySampleScan = future.get();
                }
                catch (InterruptedException | ExecutionException e1) {
                    e1.printStackTrace();
                }
            }
            if (arraySampleScan.size() != 0)
                Toast.makeText(this, "File have been successfully read!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "You need to fulfill the Data Base before to read a combo no gps!", Toast.LENGTH_SHORT).show();
            DataBase.addArraySampleScan(arraySampleScan);
            numberOfSampleScan.setText(Integer.toString(DataBase.getArraySampleScan().size()));
            numberOfWifi.setText(Integer.toString(DataBase.numberOfWifi()));
        }
    }

    private boolean read(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String firstLine = br.readLine();
            if(firstLine.contains("WigleWifi"))
                return true;
           return  false;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted !", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(this, "Permission not granted !", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    public void exportFile(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to export !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, ExportActivity.class));
    }

    public void filter(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to filter !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, FilterActivity.class));
    }

    public void clearDataBase(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "Database is already empty !", Toast.LENGTH_SHORT).show();
            return;
        }
        DataBase.clear();
        numberOfSampleScan.setText("0");
        numberOfWifi.setText("0");
    }

    public void assesLocation(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is not sample scan in the Database !", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Mac> arrayMac =  new ArrayList<Mac>();
        CastFromSampleScanToMac castFromSampleScanToMac = new CastFromSampleScanToMac();
        ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
        Future<ArrayList<Mac>> future = execut.submit(new CallableCast<SampleScan, Mac>(castFromSampleScanToMac, DataBase.getArraySampleScan()));
        while (!future.isDone());
        try {
            arrayMac = future.get();
        }
        catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        ArrayList<LineAlgo1> arrayLineAlgo1 =  new ArrayList<LineAlgo1>();
        CastFromMacToLineAlgo1 castFromMacToLineAlgo1 = new CastFromMacToLineAlgo1();
        ExecutorService execut2 = (ExecutorService) Executors.newSingleThreadExecutor();
        Future<ArrayList<LineAlgo1>> future2 = execut2.submit(new CallableCast<Mac, LineAlgo1>(castFromMacToLineAlgo1, arrayMac));
        while (!future2.isDone());
        try {
            arrayLineAlgo1 = future2.get();
        }
        catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        WriteFile writeAlgo1 = new WriteComboAlgo1("AssesLocation", this);
        Thread threadCsv = new Thread(new RunWrite<LineAlgo1>(writeAlgo1, arrayLineAlgo1));
        threadCsv.start();
        Toast.makeText(this, "File AssessLocation have been successfully downloaded !", Toast.LENGTH_SHORT).show();
    }

    public void showDataBase(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to display !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, ShowDatabaseActivity.class));
    }

}