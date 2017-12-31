package runs;

import java.util.ArrayList;

import objects.SampleScan;
import write.WriteFile;

public class RunWrite<T> implements Runnable {

	private WriteFile write;
	private ArrayList<T> array;
	
	public RunWrite(WriteFile write, ArrayList<T> array) {
		this.write = write;
 		this.array = array;
	}

	@Override
	public void run() {
		System.out.println("Beginning the writing.");
		Long start = System.currentTimeMillis();
		write.receiveData(array);
		Long end = System.currentTimeMillis();
		System.out.println("finishing the writing.");
		System.out.println("Time of the writing : " + (end - start) + "milliseconds");
	}

}
