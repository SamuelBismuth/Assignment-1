package runs;

import android.app.Activity;
import android.os.Looper;
import android.widget.Toast;

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
		for (SampleScan input : arrayInput) {
			Thread thread = new Thread(
					new RunSetCoordinates(
							input,
							(ArrayList<WeigthAverage>) DataBase.getArrayWeightAverage().clone()
							)
					);
			thread.start();
			thread.join();
		}
		return arrayInput;
	}

}
