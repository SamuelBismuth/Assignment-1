package cast;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import java.util.ArrayList;

import libraries.InputException;
import libraries.ParseDate;
import objects.LineDataBase;
import objects.LineDataBaseWifi;
import objects.SampleScan;
import objects.Wifi;

/**
 * Created by Samuel on 11/01/2018.
 */

public class CastFromLineDataBaseToSampleScan extends Cast<LineDataBase, SampleScan> {

    @Override
    public ArrayList<SampleScan> cast(ArrayList<LineDataBase> array) {
        ArrayList<SampleScan> arraySampleScan = new ArrayList<>();
        for (LineDataBase line : array) {
            try {
                ArrayList<Wifi> arrayWifi = newArrayListWifi(line);
                arraySampleScan.add(new SampleScan(
                        ParseDate.stringToDate(line.getDate()),
                        line.getId(),
                        new EarthCoordinate(
                             Double.parseDouble(line.getLatitude()),
                                Double.parseDouble(line.getLongitude()),
                                        Double.parseDouble(line.getAltitude())
                        ),
                        arrayWifi
                ));
            } catch (InputException e) {
                e.printStackTrace();
            }
        }
        return arraySampleScan;
    }

    private ArrayList<Wifi> newArrayListWifi(LineDataBase line) {
        ArrayList<Wifi> arrayWifi = new ArrayList<>();
        for (LineDataBaseWifi wifi : line.getArrayWifi())
            arrayWifi.add(new Wifi(
                    wifi.getMac(),
                    Double.parseDouble(wifi.getSignal())
            ));
        return arrayWifi;
    }
}
