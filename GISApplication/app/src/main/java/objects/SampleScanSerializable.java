package objects;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class define an object SampleScan which is serializable.
 *
 * @author Orel and Samuel.
 */
public class SampleScanSerializable implements Serializable {

    private GregorianCalendar time;
    private String id;
    private double latitude, longitude, altitude;
    private ArrayList<Wifi> arrayWifi;

    /**
     * Constructor.
     *
     * @param sampleScan
     */
    public SampleScanSerializable(SampleScan sampleScan) {
        this.time = sampleScan.getTime();
        this.id = sampleScan.getId();
        this.arrayWifi = sampleScan.getArrayWifi();
        this.latitude = sampleScan.getPointLocation().getLatitude();
        this.longitude = sampleScan.getPointLocation().getLongitude();
        this.altitude = sampleScan.getPointLocation().getAltitude();
    }

    /**
     * This function cast a SampleScan serializable to a sampleScan.
     * @return sampleScan.
     */
    public SampleScan fromSampleScanSerializableToSampleScan() {
        return new SampleScan(
                time,
                id,
                new EarthCoordinate(
                        latitude,
                        longitude,
                        altitude
                ),
                arrayWifi
        );
    }
}
