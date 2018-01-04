package read;

import com.gis.gisapplication.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import cast.Cast;
import cast.CastFromCsvFileToSampleScan;
import libraries.DataBase;
import libraries.Format;
import libraries.ReadFolder;
import objects.CsvFile;
import objects.SampleScan;
import objects.WigleWifiLine;
import runs.CallableCast;

/**
 * This class extends {@link ReadCsv} and implements @see {@link ReadFile}.
 * This class reads a file and input into an array list the object @see {@link CsvFile}.
 * This class use the API commons-csv @see https://commons.apache.org/proper/commons-csv/.
 * This class implements @see {@link ReadFile}.
 *
 * @author Orel and Samuel.
 * @see NOTICE for more informations about how to run with the api.
 */

public class ReadWigleWifi extends ReadCsv<CsvFile> implements ReadFile<WigleWifiLine> {

    private ArrayList<SampleScan> arraySampleScan = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param filePath
     * @param array
     */
    public ReadWigleWifi(String filePath, ArrayList<CsvFile> array, ArrayList<SampleScan> arraySampleScan) {
        super(filePath, array);
        this.arraySampleScan = arraySampleScan;
    }

    /**
     * This method reads the file from @see {@link ReadFolder} and put into an array list the data we need.
     * We use here the API commons-csv.
     * Attention : tu run with the API you need to import him into the project @see README.
     *
     * @throws IOException : print error reading file.
     */
    @Override
    public void readBuffer() {
        try {
            BufferedReader br = readFile(filePath);
            String firstLine = br.readLine();
            ArrayList<WigleWifiLine> arrayLine = new ArrayList<WigleWifiLine>();
            if (Format.checkTheFile(firstLine)) {
                Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(br);
                for (CSVRecord record : records)
                    arrayLine.add(inputObject(record, getId(firstLine)));
                array.add(new CsvFile(getId(firstLine), arrayLine));
                br.close();
            }
            Cast castCsv = new CastFromCsvFileToSampleScan();
            ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
            Future<ArrayList<SampleScan>> future = execut.submit(new CallableCast<CsvFile, SampleScan>(castCsv, array));
            while (!future.isDone()) ;
            try {
                arraySampleScan = future.get();
            } catch (InterruptedException | ExecutionException e1) {
                e1.printStackTrace();
            }
            DataBase.addAllArraySampleScan(arraySampleScan);
        } catch (IOException ex) {
            System.out.println("Error reading file : " + ex);
            System.exit(0);
        }
    }

    /**
     * This method create a new line.
     *
     * @param record
     * @param id
     * @return {@link WigleWifiLine}.
     */
    @Override
    public WigleWifiLine inputObject(CSVRecord record, String id) {
        return new WigleWifiLine(
                record.get("MAC"),
                record.get("SSID"),
                record.get("AuthMode"),
                record.get("FirstSeen"),
                record.get("Channel"),
                record.get("RSSI"),
                record.get("CurrentLatitude"),
                record.get("CurrentLongitude"),
                record.get("AltitudeMeters"),
                record.get("AccuracyMeters"),
                record.get("Type"),
                id
        );
    }

    //Private unimplemented methods.

    /**
     * The method finds from the first line the id.
     *
     * @param firstLine
     * @return the id.
     */
    public static String getId(String firstLine) {
        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.substring(i, i + 7).equals("display")) {
                int j = i + 8;
                while (firstLine.charAt(j) != ',' && j < firstLine.length() - 1) j++;
                return firstLine.substring(i + 8, j);
            }
        }
        return "id";
    }

}
