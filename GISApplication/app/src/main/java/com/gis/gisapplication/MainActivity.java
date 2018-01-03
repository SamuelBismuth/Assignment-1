package com.gis.gisapplication;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cast.CastFromMacToLineAlgo1;
import cast.CastFromSampleScanToMac;
import filter.Filter;
import lib.folderpicker.FolderPicker;
import libraries.DataBase;
import libraries.ReadFolder;
import objects.CsvFile;
import objects.LineAlgo1;
import objects.Mac;
import objects.SampleScan;
import read.ReadCombo;
import read.ReadCsv;
import read.ReadWigleWifi;
import runs.CallableCast;
import runs.RunWrite;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * TODO : ?? do i need to do the algo2 part 2 ?
 * TODO : WIERD THREAD WHICH FUCKS EVERYTHING AND QUESTION 3
 * TODO : seconds
 * TODO : reorganize the functions and java doc et tout le bordel
 * TODO : SEE IF THREAD IN A GOOD PLACE (always need threads ?) (answer sephie)
 * TODO : REORGANIZE ALL THE THREAD (CALABLE NEED TO BE IMPROVE : USE OF THE DATRA BASE)
 * TODO : NUMBER PICKER
 * TODO : DESIGN !!
 * TODO : TESTS
 */
public class MainActivity extends AppCompatActivity {

    static Activity thisActivity = null;

    private static TextView numberOfSampleScan, numberOfWifi;

    private static final int SDCARD_PERMISSION_FOLDER = 12,
            SDCARD_PERMISSION_FILE = 123,
            FOLDER_PICKER_CODE = 78,
            FILE_PICKER_CODE = 786;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thisActivity = this;
        numberOfSampleScan = (TextView) findViewById(R.id.numberOfSampleScan);
        numberOfWifi = (TextView) findViewById(R.id.numberOfWifi);
        clear();
    }

    public void pickFolder(View v) {
        pickFolderOrFile(true);
    }

    public void pickFile(View v) {
        pickFolderOrFile(false);
    }

    void pickFolderOrFile(boolean folder) {
        if (Build.VERSION.SDK_INT < 23) {
            if (folder)
                pickFolder();
            else
                pickFile();
        }
        else {
            if (storagePermissionAvailable()) {
                if (folder)
                    pickFolder();
                else
                    pickFile();
            }
            else {
                if (folder) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            SDCARD_PERMISSION_FOLDER);
                }
                else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            SDCARD_PERMISSION_FILE);
                }
            }
        }
    }

    boolean storagePermissionAvailable() {
        // For api Level 23 and above.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SDCARD_PERMISSION_FOLDER:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickFolder();
                }
                break;
            case SDCARD_PERMISSION_FILE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickFile();
                }
                break;
        }
    }

    void pickFolder() {
        Intent intent = new Intent(this, FolderPicker.class);
        startActivityForResult(intent, FOLDER_PICKER_CODE);
    }

    void pickFile() {
        Intent intent = new Intent(this, FolderPicker.class);
        intent.putExtra("location", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        intent.putExtra("pickFiles", true);
        startActivityForResult(intent, FILE_PICKER_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FOLDER_PICKER_CODE && resultCode == Activity.RESULT_OK) {
            String folderLocation = intent.getExtras().getString("data");
            readFolder(folderLocation);
        }
        else if (requestCode == FILE_PICKER_CODE && resultCode == Activity.RESULT_OK) {
            String fileLocation = intent.getExtras().getString("data");
            readFile(fileLocation);
        }
        if (DataBase.getFilterStack().size() != 0)
            applyingFilter();
    }

    private void readFolder(String folderLocation) {
        File[] fileArray = ReadFolder.read(folderLocation);
        for (File file : fileArray )
            readFile(file.getPath());
    }

    private void readFile(String filePath) {
        ArrayList<CsvFile> arrayCsvFile = new ArrayList<CsvFile>();
        ArrayList<SampleScan> arraySampleScan = new ArrayList<SampleScan>();
        if (read(filePath)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ReadCsv<CsvFile> readWigleWifi = new ReadWigleWifi(filePath, arrayCsvFile, arraySampleScan);
                    readWigleWifi.readBuffer();
                    MainActivity.refreshDataBase();
                    if (DataBase.getArraySampleScan().size() == 0)
                        MainActivity.toastErrorRead();
                    else
                        MainActivity.toastRead();
                }
            });
        }
        else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ReadCsv<SampleScan> readCombo = new ReadCombo(filePath, arraySampleScan);
                    readCombo.readBuffer();
                    MainActivity.refreshDataBase();
                    if (DataBase.getArraySampleScan().size() == 0)
                        MainActivity.toastErrorRead();
                    else
                        MainActivity.toastRead();
                }
            });
        }
    }

    private void applyingFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter");
        builder.setMessage("Do you want to use the previous filter in the new import?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                for (Filter filter : DataBase.getFilterStack())
                    filter.run();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
        clear();
    }

    public static void clear() {
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

    public static void refreshDataBase() {
        numberOfSampleScan.setText(Integer.toString(DataBase.getArraySampleScan().size()));
        numberOfWifi.setText(Integer.toString(DataBase.numberOfWifi()));
    }

    public void showFilter(View view) {
        if (DataBase.getFilterStack().isEmpty()) {
            Toast.makeText(this, "There is no Filter to display !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, ShowFilterActivity.class));
    }

    public static void toastRead() {
        Toast.makeText(thisActivity, "File have been successfully read!", Toast.LENGTH_SHORT).show();
    }

    public static void toastErrorRead() {
        Toast.makeText(thisActivity, "You need to fulfill the Data Base before to read a combo no gps!", Toast.LENGTH_SHORT).show();
    }

    public void macLocalisation(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, MacActivity.class));
    }
}