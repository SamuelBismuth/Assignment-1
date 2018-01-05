package cast;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import libraries.Algorithm2;
import libraries.Format;
import libraries.InputException;
import libraries.KmlUtil;
import libraries.ParseDate;
import objects.Mac;
import objects.MacInformation;
import objects.MacInformationAlgo1;
import objects.SampleScan;
import objects.Wifi;
import objects.WigleWifiLine;

/**
 * This abstract class defines one abstract method : cast and needToCreateObject.
 * Then, three classes extends this abstract class : @see {@link CastFromCsvFileToSampleScan}, @see {@link CastFromMacToLineAlgo1},
 * and @see {@link CastFromSampleScanToMac}.
 * <p>
 * The main goal of this abstract class is to cast an {@link ArrayList} of an {@link java.util.Objects} to an another ArrayList.
 * <p>
 * All the functions defined here are static for the same reason of @see {@link Algorithm2} or, @see {@link KmlUtil}.
 * <p>
 * There are two parameters which are F (From) and T (To),
 *
 * @author Orel and Samuel
 */
public abstract class Cast<F, T> {

    /**
     * Abstract method, we define it in the others classes.
     */
    public abstract ArrayList<T> cast(ArrayList<F> array);

    /**
     * This method create a new {@link Mac}.
     *
     * @param sampleScan
     * @param wifi
     * @return {@link Mac}.
     */
    protected static Mac newMac(SampleScan sampleScan, Wifi wifi) {
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
        array.add(newMacLocation(sampleScan, wifi));
        return new Mac(
                wifi.getMac(),
                array,
                sampleScan.getTime()
        );
    }

    /**
     * This method create a new {@link MacInformationAlgo1}.
     *
     * @param sampleScan
     * @param wifi
     * @return {@link MacInformationAlgo1}.
     */
    protected static MacInformationAlgo1 newMacLocation(SampleScan sampleScan, Wifi wifi) {
        return new MacInformationAlgo1(
                sampleScan.getPointLocation(),
                wifi,
                wifi.getSignal()
        );
    }

    /**
     * The method add a new scan.
     *
     * @param line
     * @return {@link SampleScan}.
     * @throws InputException : Error on the Firstseen of the csv file.
     */
    protected static SampleScan newSampleScan(WigleWifiLine line) {
        GregorianCalendar time = new GregorianCalendar();
        try {
            time = ParseDate.stringToDate(line.getFirstseen());
        } catch (InputException ex) {
            System.out.println("Error on the Firstseen of the csv file. " + ex);
        }
        ArrayList<Wifi> array = new ArrayList<Wifi>();
        if (line.getType().equals("WIFI")) array.add(newWifi(line));
        return new SampleScan(
                time,
                line.getId(),
                new EarthCoordinate(Double.parseDouble(Format.containsInterogation(line.getCurrentLongitude())),
                        Double.parseDouble(Format.containsInterogation(line.getCurrentLatitude())),
                        Double.parseDouble(Format.containsInterogation(line.getAltitudeMeters()))),
                array);
    }

    /**
     * This method create a wifi.
     *
     * @param line
     * @return wifi
     */
    protected static Wifi newWifi(WigleWifiLine line) {
        return new Wifi(
                line.getSsid(),
                line.getMac(),
                Format.channelToFrequency(Integer.parseInt(line.getChannel())),
                Double.parseDouble(line.getRssi())
        );
    }
}
