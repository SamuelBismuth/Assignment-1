package util.java.runs;

import objects.SampleScan;
import read.ReadCsv;

public class RunRead implements Runnable {

	private ReadCsv<SampleScan> readInput;
	private String combo;
	
	public RunRead(ReadCsv<SampleScan> readInput, String combo) {
		this.readInput = readInput;
		this.combo = combo;
	}
	
	@Override
	public void run() {
		System.out.println("Beginning the reading of : " + combo);
	    Long start = System.currentTimeMillis();
		readInput.readBuffer();
	    Long end = System.currentTimeMillis();
		System.out.println("finishing the reading of : " + combo);
		System.out.println("Time of the reading : " + (end - start) + "milliseconds");
	}

}
