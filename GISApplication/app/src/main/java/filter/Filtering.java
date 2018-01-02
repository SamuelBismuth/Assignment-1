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
 * This interface defines only one method : filteringBy.
 * Then, fives classes implements this interface : 
 * For the kml file : @see {@link FilteringKmlEmpty}, @see {@link FilteringKmlId}, @see {@link FilteringKmlPlace} and @see {@link FilteringKmlTime}.
 * For the csv file : @see {@link CastFromMacToLineAlgo1} also implements this interface.
 * 
 * The main goal of this interface is to filtering the data.
 * For an array of object "Y", the function filteringBy filter the data, then, return one class which implements {@link WriteFile}, with the {@link GenericDeclaration} "T".
 * 
 * @author Orel and Samuel. 
 * @param <T>.
 */
public abstract class Filtering<T> {

	private ArrayList<SampleScan> array;
	private Logic logic;
	private boolean not;
	private Filtering filter1;
	private Filtering filter2;

	/**
	 * Constructor.
	 * @param array
	 * @param logic
	 * @param not
	 * @param filter1
	 * @param filter2
	 */
	public Filtering(ArrayList<SampleScan> array, Logic logic, boolean not, Filtering filter1, Filtering filter2) {
		this.array = array;
		this.logic = logic;
		this.not = not;
		this.filter1 = filter1;
		this.filter2 = filter2;
	}

	public abstract ArrayList<T> filteringBy(ArrayList<T> array) throws InputException;
	
	/**
	 * Thanks to Yehonathan, and Yshai, that's helped me to build this function.
	 * This method receive an {@link ArrayList} of {@link SampleScan}, and return the same arrayList, but without the duplicate mac.
	 * This method use a {@link HashMap}.
	 * @param array
	 * @return array.
	 */
	public void removeDuplicateMac(ArrayList<SampleScan> array) {
		Map<String, Wifi> map = new HashMap<>();
		array.forEach(sampleScan -> sampleScan.getArrayWifi().forEach(wifi -> {
			if (map.containsKey(wifi.getMac())) {
				if (wifi.compareTo(map.get(wifi.getMac())) == 1) {
					map.put(wifi.getMac(), wifi);
				}
			}
			else {
				map.put(wifi.getMac(), wifi);
			}
		}));
		array.forEach(sampleScan -> sampleScan.getArrayWifi()
				.removeIf(wifiSpot -> !wifiSpot.equals(map.get(wifiSpot.getMac()))));
	}

	public ArrayList<SampleScan> run() {
		ArrayList<SampleScan> arrayFilter1 = new ArrayList<SampleScan>();
		ArrayList<SampleScan> arrayFilter2 = new ArrayList<SampleScan>();
		ArrayList<SampleScan> arrayFinal = new ArrayList<SampleScan>();
		ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
		Future<ArrayList<SampleScan>> futureArrayFilter1 = null;
		Future<ArrayList<SampleScan>> futureArrayFilter2 = null;
		if (filter1 != null)
			futureArrayFilter1 = execut.submit(new CallableFiltering<SampleScan>(filter1, (ArrayList<SampleScan>) array.clone()));
		if (filter2 != null)
			futureArrayFilter2 = execut.submit(new CallableFiltering<SampleScan>(filter2, (ArrayList<SampleScan>) array.clone()));
		if (filter2 != null)
			while (!futureArrayFilter1.isDone() && !futureArrayFilter2.isDone());
		else
			while (!futureArrayFilter1.isDone());
		try {
			arrayFilter1 = futureArrayFilter1.get();
			if (filter2 != null)
				arrayFilter2 = futureArrayFilter2.get();
		}
		catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		switch (this.logic.getLogic()) {
			case "&&":
				arrayFilter1.retainAll(arrayFilter2);
				break;
			case "||":
				arrayFilter1.removeAll(arrayFilter2);
				arrayFilter1.addAll(arrayFilter2);
				break;
			case "none":
				break;
			default:
		}
		return arrayFilter1;
	}

}
