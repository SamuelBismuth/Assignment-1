package com.gis.gisapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.boehn.kmlframework.kml.Document;

import libraries.DataBase;
import objects.SampleScan;
import runs.RunWrite;
import write.WriteCombo;
import write.WriteFile;
import write.WriteKml;

/**
 * This class is the export activity.
 * From here, the user can export this session, either in a kml file or a csv file.
 *
 * @author Orel and Samuel.
 */
public class ExportActivity extends AppCompatActivity {

    private Switch kml, csv;
    private EditText fileName;

    /**
     * This is the Oncreate method/constructor.
     * This constructor is activate once and only once, when the user open the activity for the first time.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        kml = (Switch) findViewById(R.id.kml);
        csv = (Switch) findViewById(R.id.csv);
        fileName = (EditText) findViewById(R.id.fileName);
    }

    /**
     * This method define the onClick of the button EXPORT FILE.
     * The method checks the choice od the user, and then, write the files.
     * Here the use of threads is really import, to make the writing faster, and, obviously, allow the user to continue his use.
     *
     * @param view
     */
    public void export(View view) {
        if (DataBase.getArraySampleScan().size() == 0) {
            Toast.makeText(this, "Your Data base is empty, there is nothing to export!", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (kml.isChecked()) {
            Document document = new Document();
            WriteFile writeKml = new WriteKml(fileName.getText().toString(), document);
            Thread threadKml = new Thread(new RunWrite<SampleScan>(writeKml, DataBase.getArraySampleScan()));
            threadKml.start();
        }
        if (csv.isChecked()) {
            WriteFile writeCsv = new WriteCombo(fileName.getText().toString(), this);
            Thread threadCsv = new Thread(new RunWrite<SampleScan>(writeCsv, DataBase.getArraySampleScan()));
            threadCsv.start();
        }
        if (kml.isChecked() || csv.isChecked()) {
            Toast.makeText(this, "File(s) have been successfully export in Download file!", Toast.LENGTH_SHORT).show();
            finish();
        } else
            Toast.makeText(this, "You must select at least file!", Toast.LENGTH_SHORT).show();
    }
}
