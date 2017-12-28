package util.java.runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import filter.Filtering;

public class CallableFiltering<T> implements Callable<ArrayList<T>> {
	
	private Filtering<T> filter;
	private ArrayList<T> array;
	
	public CallableFiltering(Filtering<T> filter, ArrayList<T> array) {
		this.filter = filter;
		this.array = array;
	}

	@Override
	public ArrayList<T> call() throws Exception {
		System.out.println("Beginning the" + filter.toString());
		return filter.filteringBy(array);
	}

}
