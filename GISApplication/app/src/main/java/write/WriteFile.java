package write;

import android.os.Environment;

import java.util.ArrayList;

import libraries.InputException;
import objects.SampleScan;

/**
 * This abstract class defines three abstract method : receiveData, writeHeader,  writeFile.
 *
 * Then, three classes extends this abstract class :
 * @see {@link WriteKml}, {@link WriteCombo}, {@link WriteComboAlgo1}.
 * <p>
 * The main goal of this interface is to write a file.
 * The class are able to write a kml or a cvs file.
 *
 * @author Orel and Samuel.
 */
public abstract class WriteFile<T> {

    public abstract void receiveData(ArrayList<T> array);

    public abstract void writeHeader();

    public abstract void writeFile() throws InputException;

    /**
     * @return true if the external storage is available.
     */
    public boolean externalStorageAvailable() {
        return
                Environment.MEDIA_MOUNTED
                        .equals(Environment.getExternalStorageState());
    }
}
