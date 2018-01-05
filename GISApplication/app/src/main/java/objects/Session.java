package objects;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import libraries.DataBase;

/**
 * This class define an object session.
 *
 * @author Orel and Samuel.
 */
public class Session implements Serializable {

    private Stack<Filter> filterStack = new Stack<Filter>();
    private ArrayList<File> arrayFile = new ArrayList<File>();
    private ArrayList<SampleScanSerializable> arraySampleScanSerializable = new ArrayList<SampleScanSerializable>();

    /**
     * Constructor.
     *
     * @param filterStack
     * @param arrayFile
     * @param arraySampleScan
     */
    public Session(Stack<Filter> filterStack, ArrayList<File> arrayFile, ArrayList<SampleScan> arraySampleScan) {
        this.filterStack = filterStack;
        this.arrayFile = arrayFile;
        fromArraySampleScanToArraySampleScanSerializable(arraySampleScan);
    }

    /**
     * @param arraySampleScan
     */
    private void fromArraySampleScanToArraySampleScanSerializable(ArrayList<SampleScan> arraySampleScan) {
        for (SampleScan sampleScan : arraySampleScan)
            arraySampleScanSerializable.add(new SampleScanSerializable(sampleScan));
    }

    /**
     * This function save the session with a Serializable.
     *
     * @param filename
     */
    public void saveSession(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename + ".ser"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * This function refresh the Database.
     */
    public void refreshDataBase() {
        DataBase.addAllArrayFile(arrayFile);
        DataBase.pushAllSack(filterStack);
        DataBase.addAllArraySampleScan(fromArraySampleScanSerializableToarraySampleScan());
    }

    /**
     * @return array {@link SampleScan}.
     */
    public ArrayList<SampleScan> fromArraySampleScanSerializableToarraySampleScan() {
        ArrayList<SampleScan> array = new ArrayList<>();
        for (SampleScanSerializable sampleScanSerializable : arraySampleScanSerializable)
            array.add(sampleScanSerializable.fromSampleScanSerializableToSampleScan());
        return array;
    }
}

