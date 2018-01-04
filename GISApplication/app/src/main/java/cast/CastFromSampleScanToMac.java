package cast;

import java.util.ArrayList;

import objects.Mac;
import objects.SampleScan;
import objects.Wifi;

public class CastFromSampleScanToMac extends Cast<SampleScan, Mac> {

    /**
     * This method create an {@link ArrayList} of {@link Mac from an ArrayList of {@link SampleScan}.
     * In the object {@link Mac} is found all the information needed to create the new weight coordinates.
     *
     * @param arraySampleScan
     * @return  array.
     */
    @Override
    public ArrayList<Mac> cast(ArrayList<SampleScan> arraySampleScan) {
        ArrayList<Mac> arrayMac = new ArrayList<Mac>();
        for (SampleScan sampleScan : arraySampleScan)
            for (Wifi wifi : sampleScan.getArrayStrongerWifi()) {
                if (arrayMac.size() != 0 && needToCreateObject(wifi.getMac(), arrayMac.get(arrayMac.size() - 1))) {
                    arrayMac.get(arrayMac.size() - 1).getArrayMacLocation().add(newMacLocation(sampleScan, wifi));
                } else arrayMac.add(newMac(sampleScan, wifi));

            }
        for (Mac mac : arrayMac) mac.sort(mac.getArrayMacLocation());
        return arrayMac;
    }

    /**
     * This method should say if needs to create a {@link Mac} object.
     *
     * @param mac
     * @param object
     * @return false if doesn't need.
     */
    public boolean needToCreateObject(String mac, Object object) {
        Mac macLocation = (Mac) object;
        return macLocation.getMacName().equals(mac);
    }

}
