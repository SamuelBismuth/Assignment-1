package runs;

import java.util.ArrayList;

import write.WriteFile;

public class RunWrite<T> implements Runnable {

	private WriteFile<T> write;
	private String file;
	private ArrayList<T> array;
	
	public RunWrite(WriteFile<T> write, String file, ArrayList<T> array) {
		this.write = write;
 		this.file = file;
 		this.array = array;
	}

	@Override
	public void run() {
		System.out.println("Beginning the write of : " + file);
		Long start = System.currentTimeMillis();
		write.receiveData(array);
		Long end = System.currentTimeMillis();
		System.out.println("finishing the writing of : " + file);
		System.out.println("Time of the wrinting : " + (end - start) + "milliseconds");
	}

}
