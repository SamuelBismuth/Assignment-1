package threads;

import java.util.ArrayList;

import libraries.Algorithm2;
import objects.WeigthAverage;
import read.SampleScan;

public class RunAlgorithm2 implements Runnable {
	
	private SampleScan input;
	private ArrayList<WeigthAverage> arrayData;
	private ArrayList<WeigthAverage> arrayTemp;
	
	public RunAlgorithm2(SampleScan input, ArrayList<WeigthAverage> arrayData, ArrayList<WeigthAverage> arrayTemp) {
		this.input = input;
		this.arrayData = arrayData;
		this.arrayTemp = arrayTemp;
	}

	@Override
	public void run() {
		System.out.println("Beginning the algorithm 2 for the input : " + input.getTime().getTime().toString());
	    Long start = System.currentTimeMillis();
	    Algorithm2.mmeset(arrayData);
		arrayTemp = Algorithm2.setArrayRelevantNumber(input, arrayData);
		double sum = 0;
		for (WeigthAverage scan : arrayData) {
			sum += scan.getRelevantNumber();
		}
		if (sum != 0) {
			Algorithm2.setArrayPi(input, arrayTemp);
			Algorithm2.setLocation(input, arrayTemp);
		}
		Long end = System.currentTimeMillis();
		System.out.println("finishing the algorithm 2 for the input : " + input.getTime().getTime().toString());
		System.out.println("Running time : " + (end - start) + "milliseconds");
	}

}
