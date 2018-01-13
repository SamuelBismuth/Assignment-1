package com.gis.gisapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import cast.Cast;
import cast.CastFromLineDataBaseToSampleScan;
import cast.CastFromMacToLineAlgo1;
import cast.CastFromSampleScanToMac;
import objects.Filter;
import lib.folderpicker.FolderPicker;
import libraries.DataBase;
import libraries.ReadFolder;
import objects.CsvFile;
import objects.LineAlgo1;
import objects.LineDataBase;
import objects.Mac;
import objects.SampleScan;
import objects.Session;
import read.ReadCombo;
import read.ReadCsv;
import read.ReadWigleWifi;
import runs.CallableCast;
import runs.DataBaseObserver;
import runs.RunWrite;
import runs.RunFileModification;
import sql.MySql;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * This class is the main activity.
 * From this class all onClick are define.
 * This main class is the root of the GUI.
 * <p>
 * IMPORTANT NOTE : Here are use a library take from github : https://github.com/kashifo/android-folder-picker-library?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=5837.
 * Also, all the functions which are in link to the file chooser are taken from the github project.
 *
 * @author Orel and Samuel.
 */
public class MainActivity extends AppCompatActivity {

    static Activity thisActivity = null;

    public static TextView numberOfSampleScan, numberOfWifi;

    protected static boolean isVisible = false;

    private static final int SDCARD_PERMISSION_FOLDER = 12,
            SDCARD_PERMISSION_FILE = 123,
            SDCARD_PERMISSION_Session = 453,
            FOLDER_PICKER_CODE = 78,
            FILE_PICKER_CODE = 786,
            Session_PICKER_CODE = 124;

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
        setContentView(R.layout.activity_main);
        thisActivity = this;
        numberOfSampleScan = (TextView) findViewById(R.id.numberOfSampleScan);
        numberOfWifi = (TextView) findViewById(R.id.numberOfWifi);
        clear();
    }

    /**
     * This is the onResume method/constructor.
     * This constructor is activate all the times that the user open the activity.
     */
    @Override
    public void onResume() {
        super.onResume();
        isVisible = true;
        setVisible(true);
    }

    /**
     * This is the onPause method/constructor.
     * This constructor is activate all the times that the user leave the acticity.
     */
    @Override
    public void onPause() {
        super.onPause();
        isVisible = false;
        setVisible(false);
    }

    /**
     * @return the boolean visible.
     */
    public boolean isVisble() {
        return isVisible;
    }

    /**
     * This function is call if the user want to pick a folder.
     *
     * @param v
     */
    public void pickFolder(View v) {
        pickFolderOrFile("Folder");
    }

    /**
     * This function is call if the user want to pick a file.
     *
     * @param v
     */
    public void pickFile(View v) {
        pickFolderOrFile("File");
    }

    /**
     * This function is call if the user want to pick a session.
     *
     * @param v
     */
    public void pickSession(View v) {
        pickFolderOrFile("Session");
    }

    /**
     * This method decide if the need of the user is to import a file or to import a folder.
     *
     * @param type
     */
    void pickFolderOrFile(String type) {
        if (Build.VERSION.SDK_INT < 23) {
            switch (type) {
                case "Folder":
                    pickFolder();
                    break;
                case "File":
                    pickFile();
                    break;
                case "Session":
                    pickSession();
                    break;
            }
        } else {
            if (storagePermissionAvailable()) {
                switch (type) {
                    case "Folder":
                        pickFolder();
                        break;
                    case "File":
                        pickFile();
                        break;
                    case "Session":
                        pickSession();
                        break;
                }
            } else {
                switch (type) {
                    case "Folder":
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                SDCARD_PERMISSION_FOLDER);
                        break;
                    case "File":
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                SDCARD_PERMISSION_FILE);
                        break;
                    case "Session":
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                SDCARD_PERMISSION_Session);
                        break;
                }
            }
        }
    }

    /**
     * This function ask for the authorization to use the storage.
     *
     * @return
     */
    boolean storagePermissionAvailable() {
        // For api Level 23 and above.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else
            return true;
    }

    /**
     * This function call either a pickFolder, pickFile or the pickSession function by the code given in a final constant global integer.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
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
            case SDCARD_PERMISSION_Session:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickSession();
                }
                break;
        }
    }

    /**
     * Pick a folder.
     */
    void pickFolder() {
        Intent intent = new Intent(this, FolderPicker.class);
        startActivityForResult(intent, FOLDER_PICKER_CODE);
    }

    /**
     * Pick a file.
     */
    void pickFile() {
        Intent intent = new Intent(this, FolderPicker.class);
        intent.putExtra("location", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        intent.putExtra("pickFiles", true);
        startActivityForResult(intent, FILE_PICKER_CODE);
    }

    /**
     * Pick a session.
     */
    void pickSession() {
        Intent intent = new Intent(this, FolderPicker.class);
        intent.putExtra("location", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        intent.putExtra("pickFiles", true);
        startActivityForResult(intent, Session_PICKER_CODE);
    }

    /**
     * The function receive the path of the folder/file choose by the user, and then, send the path to some others
     * function which are able to read it and import it.
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FOLDER_PICKER_CODE && resultCode == Activity.RESULT_OK) {
            String folderLocation = intent.getExtras().getString("data");
            readFolder(folderLocation, true);
        } else if (requestCode == FILE_PICKER_CODE && resultCode == Activity.RESULT_OK) {
            String fileLocation = intent.getExtras().getString("data");
            readFile(fileLocation, true);
        } else if (requestCode == Session_PICKER_CODE && resultCode == Activity.RESULT_OK) {
            String filterLocation = intent.getExtras().getString("data");
            readSession(filterLocation);
        }
        if ((requestCode == FILE_PICKER_CODE || requestCode == FOLDER_PICKER_CODE) && DataBase.getFilterStack().size() != 0)
            applyingFilter();
    }

    /**
     * This function read a folder.
     *
     * @param folderLocation
     * @param flag
     */
    protected void readFolder(String folderLocation, boolean flag) {
        if (flag) {
            try {
                runOnUiThread(new RunFileModification(folderLocation));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        File[] fileArray = ReadFolder.read(folderLocation);
        DataBase.addArrayFile(new File(folderLocation));
        for (File file : fileArray)
            readFile(file.getPath(), flag);
    }

    /**
     * This function read a file.
     *
     * @param filePath
     * @param flag
     */
    protected void readFile(String filePath, boolean flag) {
        if (flag) {
            try {
                runOnUiThread(new RunFileModification(filePath));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DataBase.addArrayFile(new File(filePath));
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
        } else {
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
        File file = new File(filePath);
        DataBase.addMap(file.getName(), arraySampleScan);
    }

    /**
     * This function read a session.
     *
     * @param filterLocation
     */
    public void readSession(String filterLocation) {
        Session session = null;
        try {
            FileInputStream fileIn = new FileInputStream(filterLocation);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            session = (Session) in.readObject();
            session.refreshDataBase();
            Toast.makeText(this, "Session have been successfully read!", Toast.LENGTH_SHORT).show();
            refreshDataBase();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    /**
     * This function is call when  the user wants to save the current session.
     * The function displays a alert dialog to ask a filename to the user.
     *
     * @param view
     */
    public void saveSession(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to save !", Toast.LENGTH_SHORT).show();
            return;
        }
        LayoutInflater linf = LayoutInflater.from(this);
        final View inflator = linf.inflate(R.layout.save_session, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Filename chooser");
        alert.setMessage("Please, choose a filename for the save");
        alert.setView(inflator);
        final EditText editText = (EditText) inflator.findViewById(R.id.filenameSession);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String filename = editText.getText().toString();
                Session session = new Session(
                        DataBase.getFilterStack(),
                        DataBase.getArrayFile(),
                        DataBase.getArraySampleScan()
                );
                session.saveSession(filename);
                Toast.makeText(thisActivity, "Save successfull !", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    /**
     * This function display an alert dialog to aks the user if he wants to applying the filter he build in the new import
     * (Assuming that a new import have been done).
     */
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

    /**
     * This function is return true if the file is a wiggle wifi file.
     *
     * @param filePath
     * @return false is it's not.
     */
    private boolean read(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String firstLine = br.readLine();
            br.close();
            if (firstLine.contains("WigleWifi"))
                return true;
            return false;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is call when the user want to make a export.
     *
     * @param view
     */
    public void exportFile(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to export !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, ExportActivity.class));
    }

    /**
     * This method is call when the user want to see all the filter made.
     *
     * @param view
     */
    public void filter(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to filter !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, FilterActivity.class));
    }

    /**
     * This method clears the Database when the user click in the button "CLEAR DATABASE".
     *
     * @param view
     */
    public void clearDataBase(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "Database is already empty !", Toast.LENGTH_SHORT).show();
            return;
        }
        clear();
    }

    /**
     * This function clear the Database.
     */
    public static void clear() {
        DataBase.clear();
        numberOfSampleScan.setText("0");
        numberOfWifi.setText("0");
    }

    /**
     * This function is call when the user want to export a csv file which contains the data of the algorithm 1.
     *
     * @param view
     */
    public void assesLocation(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is not sample scan in the Database !", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Mac> arrayMac = new ArrayList<Mac>();
        CastFromSampleScanToMac castFromSampleScanToMac = new CastFromSampleScanToMac();
        ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
        Future<ArrayList<Mac>> future = execut.submit(new CallableCast<SampleScan, Mac>(castFromSampleScanToMac, DataBase.getArraySampleScan()));
        while (!future.isDone()) ;
        try {
            arrayMac = future.get();
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        ArrayList<LineAlgo1> arrayLineAlgo1 = new ArrayList<LineAlgo1>();
        CastFromMacToLineAlgo1 castFromMacToLineAlgo1 = new CastFromMacToLineAlgo1();
        ExecutorService execut2 = (ExecutorService) Executors.newSingleThreadExecutor();
        Future<ArrayList<LineAlgo1>> future2 = execut2.submit(new CallableCast<Mac, LineAlgo1>(castFromMacToLineAlgo1, arrayMac));
        while (!future2.isDone()) ;
        try {
            arrayLineAlgo1 = future2.get();
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        WriteFile<LineAlgo1> writeAlgo1 = new WriteComboAlgo1("AssesLocation");
        Thread threadCsv = new Thread(new RunWrite<LineAlgo1>(writeAlgo1, arrayLineAlgo1));
        threadCsv.start();
        Toast.makeText(this, "File AssessLocation have been successfully downloaded !", Toast.LENGTH_SHORT).show();
    }

    /**
     * This function is call when the user wants to see the database.
     *
     * @param view
     */
    public void showDataBase(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database to display !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, ShowDatabaseActivity.class));
    }

    /**
     * This method refresh the Database.
     */
    public static void refreshDataBase() {
        numberOfSampleScan.setText(Integer.toString(DataBase.getArraySampleScan().size()));
        numberOfWifi.setText(Integer.toString(DataBase.numberOfWifi()));
    }

    /**
     * This function is call when the user wants to view the database.
     *
     * @param view
     */
    public void showFilter(View view) {
        if (DataBase.getFilterStack().isEmpty()) {
            Toast.makeText(this, "There is no Filter to display !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, ShowFilterActivity.class));
    }

    /**
     * This function displays a toast message.
     */
    public static void toastRead() {
        Toast.makeText(thisActivity, "File have been successfully read!", Toast.LENGTH_SHORT).show();
    }

    /**
     * This function displays a toast message.
     */
    public static void toastErrorRead() {
        Toast.makeText(thisActivity, "You need to fulfill the Data Base before to read a combo no gps!", Toast.LENGTH_SHORT).show();
    }

    /**
     * This function is call when the user want to use both algorithm (1 and 2) and calculate some data.
     *
     * @param view
     */
    public void macLocalisation(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "There is no Database !", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(MainActivity.this, MacActivity.class));
    }

    /**
     * @return the contecxt of the {@link MainActivity}.
     */
    public Context getContextMainActivity() {
        return this;
    }

    /**
     * This function remove a file from the Database.
     *
     * @param path
     */
    protected void removeFile(String path) {
        while (!isVisble()) ;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while (!isVisble()) ;
                File file = new File(path);
                DataBase.removeArraySampleScan(DataBase.getMap().get(file.getPath()));
                refreshDataBase();
                DataBase.removeMap(file.getPath());
            }
        });
    }

    /**
     * This function is call when a modification have been made in the folder/file which are imported into the session.
     *
     * @param path
     */
    protected void modificationFileAppears(String path) {
        while (!isVisble()) ;
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + path);
        if (DataBase.getMap().containsKey(path))
            removeFile(path);
        readFile(file.getAbsolutePath(), false);
    }

    /**
     * This function allows to read in the my sql data base of our professor Boaz.
     * To do this, we ask from the user to enter the password, the port, the ip, the username, the table and the server.
     * If the reading have been done successfully, we send a thread which always check if the data from the data base is changed.
     * If the data is changed, we apply the change into our database.
     *
     * @param view
     */
    public void pickFromDataBase(View view) {
        LayoutInflater linf = LayoutInflater.from(this);
        final View inflator = linf.inflate(R.layout.database, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Database chooser");
        alert.setMessage("Please, enter all the fields");
        alert.setView(inflator);
        final EditText port = (EditText) inflator.findViewById(R.id.port);
        final EditText password = (EditText) inflator.findViewById(R.id.password);
        final EditText ip = (EditText) inflator.findViewById(R.id.ip);
        final EditText user = (EditText) inflator.findViewById(R.id.user);
        final EditText table = (EditText) inflator.findViewById(R.id.table);
        final EditText server = (EditText) inflator.findViewById(R.id.server);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ArrayList<LineDataBase> array = MySql.pickFromDataBase(
                                    port.getText().toString(),
                                    password.getText().toString(),
                                    ip.getText().toString(),
                                    user.getText().toString(),
                                    table.getText().toString(),
                                    server.getText().toString()
                            );
                            Cast cast = new CastFromLineDataBaseToSampleScan();
                            ArrayList<SampleScan> arraySampleScan = cast.cast(array);
                            DataBase.addMapSql(table.getText().toString(), arraySampleScan);
                            DataBase.addAllArraySampleScan(arraySampleScan);
                            MainActivity.refreshDataBase();
                            Toast.makeText(thisActivity, "The data of the server have been add to the DataBase !", Toast.LENGTH_SHORT).show();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    new DataBaseObserver(
                                            port.getText().toString(),
                                            password.getText().toString(),
                                            ip.getText().toString(),
                                            user.getText().toString(),
                                            table.getText().toString(),
                                            server.getText().toString()
                                    ).startService();
                                }
                            }).start();
                        } catch (SQLException ex) {
                            Logger lgr = Logger.getLogger(MySql.class.getName());
                            lgr.log(Level.SEVERE, ex.getMessage(), ex);
                            Toast.makeText(thisActivity, "Error, the data is not available !", Toast.LENGTH_SHORT).show();
                            return;
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(thisActivity, "Error, the data is not available !", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                                return;
                            }
                        });
                    }
                });
            }
        });
        alert.show();
    }

}