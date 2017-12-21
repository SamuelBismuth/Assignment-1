package threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import read.SortWigleWifi;

public class CallableSort<T, Y> implements Callable<ArrayList<T>> {

	private SortWigleWifi<T, Y> sort;
	private ArrayList<Y> arrayScan;
	private ArrayList<T> arrayMac;
	
	public CallableSort(SortWigleWifi<T, Y> sort, ArrayList<Y> arrayScan, ArrayList<T> arrayMac) {
		this.sort = sort;
		this.arrayScan = arrayScan;
		this.arrayMac = arrayMac;
	}

	@Override
	public ArrayList<T> call() throws Exception {
		arrayMac = sort.sortBy(arrayScan);
		return arrayMac;
	}
	
}
