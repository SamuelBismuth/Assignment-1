package util.java.runs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import libraries.DataBase;
import objects.SampleScan;
import objects.WeigthAverage;

public class CallableAlgorithm2 implements Callable<ArrayList<SampleScan>> {
	
	private ArrayList<SampleScan> arrayInput;
	
	public CallableAlgorithm2(ArrayList<SampleScan> arrayInput) {
		this.arrayInput = arrayInput;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SampleScan> call() throws Exception {
		System.out.println("Beginning to run the algorithm 2");
		Long start = System.currentTimeMillis();

		for (SampleScan input : arrayInput) {
			Thread thread = new Thread(
					new RunSetCoordinates(
							input,
							(ArrayList<WeigthAverage>) DataBase.getArrayWeightAverage().clone()
							)
					);
			thread.start();
		}
		Long end = System.currentTimeMillis();
		System.out.println("finishing to run the algorithm");
		System.out.println("Time of the runnning : " + (end - start) + "milliseconds");
		return arrayInput;
	}

}
