package threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import filter.Filtering;
import filter.FilteringCsvMac;
import libraries.InputException;
import objects.Mac;
import write.WriteFile;

public class CallableFiltering implements Callable<WriteArray> {

	private Filtering<Mac> filter;		
	private ArrayList<Mac> arrayMac;

	public CallableFiltering(Filtering<Mac> filter, ArrayList<Mac> arrayMac) {
		this.filter = filter;
		this.arrayMac = arrayMac;
	}

	@Override
	public WriteArray call() throws Exception {
		filter = new FilteringCsvMac(false);
		try {
			WriteFile<Mac> write = filter.filteringBy(arrayMac);
			arrayMac.get(0).getLineAlgo1().getIndex();
			return new WriteArray(
					write, 
					arrayMac
					);
		} 
		catch (InputException e) {
			e.printStackTrace();
			return null;
		}
	}

}
