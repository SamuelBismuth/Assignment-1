package filter;

import android.app.Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import libraries.InputException;
import objects.CsvFile;
import objects.Logic;
import objects.SampleScan;
import objects.Wifi;
import runs.CallableCast;
import runs.CallableFiltering;
import write.WriteFile;

/**
 * This abstract class defines only one abstract method : filteringBy.
 * Then, three classes implements this interface :
 *
 * @param <T>
 * @author Orel and Samuel.
 * @see FilteringKmlId}, @see {@link FilteringKmlPlace}, @see {@link FilteringKmlTime}
 * <p>
 * The main goal of this interface is to filtering the data.
 * For an array of object "Y", the function filteringBy filter the data, then, return one class which implements {@link WriteFile}, with the {@link GenericDeclaration} "T".
 */
public abstract class Filtering<T> {

    public abstract ArrayList<T> filteringBy(ArrayList<T> array) throws InputException;

    /**
     * Thanks to Yehonathan, and Yishay, that helped me to build this function.
     * This method receive an {@link ArrayList} of {@link SampleScan}, and return the same arrayList, but without the duplicate mac.
     * This method use a {@link HashMap}.
     *
     * @param array
     * @return array.
     */
    public void removeDuplicateMac(ArrayList<SampleScan> array) {
        Map<String, Wifi> map = new HashMap<>();
        for (SampleScan sampleScan : array) {
            for (Wifi wifi : sampleScan.getArrayStrongerWifi()) {
                if (map.containsKey(wifi.getMac())) {
                    if (wifi.compareTo(map.get(wifi.getMac())) == 1) {
                        map.put(wifi.getMac(), wifi);
                    }
                } else {
                    map.put(wifi.getMac(), wifi);
                }
            }
        }
        ArrayList<SampleScan> arrayClone = (ArrayList<SampleScan>) array.clone();
        for (SampleScan sampleScan : array) {
            for (Wifi wifi : sampleScan.getArrayWifi()) {
                if (map.get(wifi.getMac()).equals(wifi))
                    arrayClone.remove(wifi);
            }
        }
        array = arrayClone;
    }

}
