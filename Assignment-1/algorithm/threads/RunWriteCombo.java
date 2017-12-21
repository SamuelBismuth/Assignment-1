package threads;

import java.util.ArrayList;

import write.WriteFile;

public class RunWriteCombo<T> implements Runnable {

	private WriteFile<T> write;
	private String combo;
	private ArrayList<T> array;
	
	public RunWriteCombo(WriteFile<T> write, String combo, ArrayList<T> array) {
		this.write = write;
 		this.combo = combo;
 		this.array = array;
	}

	@Override
	public void run() {
		System.out.println("Beginning the write of : " + combo);
		Long start = System.currentTimeMillis();
		write.receiveData(array);
		Long end = System.currentTimeMillis();
		System.out.println("finishing the writing of : " + combo);
		System.out.println("Time of the wrinting : " + (end - start) + "milliseconds");
	}

}
