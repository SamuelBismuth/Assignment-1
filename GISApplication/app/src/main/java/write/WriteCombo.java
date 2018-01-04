package write;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import objects.SampleScan;
import objects.Wifi;

/**
 * This class writes the csv file.
 * This class implement @see {@link WriteFile}.
 *
 * @author Orel and Samuel.
 */
public class WriteCombo extends WriteFile<SampleScan> {

    private Activity activity;
    private PrintWriter outs;

    /**
     * Constructor.
     *
     * @param fileName.
     * @throws IOException : Error writing the file.
     */
    public WriteCombo(String fileName, Activity activity) {
        try {
            this.activity = activity;
            fileName += ".csv";
            if (externalStorageAvailable())
                this.outs = new PrintWriter(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method receives the data and for all the scan into the array, write the csv file.
     *
     * @param array.
     */
    @Override
    public void receiveData(ArrayList<SampleScan> array) {
        writeHeader();
        for (SampleScan scan : array) {
            this.outs.print(scan.getTime().getTime() + ",");
            this.outs.print(scan.getId() + ",");
            if (scan.getPointLocation().getLatitude() != 0)
                this.outs.print(scan.getPointLocation().getLatitude() + ",");
            else this.outs.print("null,");
            if (scan.getPointLocation().getLongitude() != 0)
                this.outs.print(scan.getPointLocation().getLongitude() + ",");
            else this.outs.print("null,");
            if (scan.getPointLocation().getAltitude() != 0)
                this.outs.print(scan.getPointLocation().getAltitude() + ",");
            else this.outs.print("null,");
            this.outs.print(scan.getWifiNetworks() + ",");
            addNetwork(scan);
        }
        writeFile();
    }

    /**
     * This method write the header of the file we need to write.
     */
    @Override
    public void writeHeader() {
        this.outs.println("Time," + "ID," + "Lat," + "Lon," + "Alt," + "#Wifi networks," + "SSID1," + "MAC1," + "Frequency1," + "Signal1," +
                "SSID2," + "MAC2," + "Frequency2," + "Signal2," + "SSID3," + "MAC3," + "Frequency3," + "Signal3," + "SSID4," + "MAC4," +
                "Frequency4," + "Signal4," + "SSID5," + "MAC5," + "Frequency5," + "Signal5," + "SSID6," + "MAC6," + "Frequency6," +
                "Signal6," + "SSID7," + "MAC7," + "Frequency7," + "Signal7," + "SSID8," + "MAC8," + "Frequency8," + "Signal8," +
                "SSID9," + "MAC9," + "Frequency9," + "Signal9," + "SSID10," + "MAC10," + "Frequency10," + "Signal10,");
    }

    /**
     * This method write the file by closing the method.
     *
     * @throws IOException : Error writing file.
     */
    @Override
    public void writeFile() {
        this.outs.close();
    }

    //Private unimplemented method.

    /**
     * This method write the data we need.
     *
     * @param scan.
     */
    private void addNetwork(SampleScan scan) {
        for (Wifi wifi : scan.getArrayStrongerWifi()) {
            this.outs.print(wifi.getName() + ",");
            this.outs.print(wifi.getMac() + ",");
            this.outs.print(wifi.getFrequency() + ",");
            this.outs.print(wifi.getSignal() + ",");
        }
        this.outs.println();
    }
}