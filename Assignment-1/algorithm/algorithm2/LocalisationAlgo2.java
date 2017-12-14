package algorithm2;

import java.util.ArrayList;
import java.util.Collections;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import read.SampleScan;
import read.Wifi;

public class LocalisationAlgo2 {

	private ArrayList<SampleScan> array;

	private final int NBOFMAC = 4;
	private final int power = 2;
	private final int norm = 10000;
	private final double sigDif = 0.4;
	private final int minDif = 3;
	private int noSignal = -120;
	private int difNoSignal = 100;

	protected LocalisationAlgo2(ArrayList<SampleScan> array) {
		this.array = array;
	}

	protected SampleScan newCoordinate(SampleScan input) {
		ArrayList<SampleScan> relevantScan = getRevelantScan(input);
		ArrayList<Double> arrayOfPi = new ArrayList<Double>();
		for (SampleScan data : relevantScan) 
			arrayOfPi.add(getPi(input, data));
		Collections.sort(arrayOfPi);
		EarthCoordinate newLocalisation = newPointLocalisation(arrayOfPi, input);

		return input;
	}

//	private EarthCoordinate newPointLocalisation(ArrayList<Double> arrayOfPi, Scan input) {
//		//MacLocation macLocation = new MacLocation(pointLocation, d)
//	}



	private double getPi(SampleScan input, SampleScan data) {
		int pi = 1;
		int count = 0;
		for (Wifi wifi : data.getArrayStrongerWifi()) {
			double diff = getDiff(input, wifi, count);
			double weight = getWeight(input, diff, count);
			pi *= weight;
		}
		return pi;
	}

	private double getDiff(SampleScan input, Wifi wifi, int count) {
		if(wifi.getSignal() == noSignal) return difNoSignal;
		else return Math.max(
				minDif,
				Math.abs(
						input.getArrayStrongerWifi().get(count).getSignal()
						- wifi.getSignal()
						)
				);
	}

	private double getWeight(SampleScan input, double diff, int count) {
		return
				norm / (
						Math.pow(
								diff,
								sigDif
								) *
						Math.pow(
								input.getArrayStrongerWifi().get(count).getSignal(),
								power
								)
						);

	}

	private ArrayList<SampleScan> getRevelantScan(SampleScan input) {
		ArrayList<SampleScan> relevantScan = new ArrayList<SampleScan>();
		ArrayList<SampleScan> arrayClone = (ArrayList<SampleScan>) array.clone();
		for (SampleScan data : arrayClone) data.setRelevantNumber(input);
		Collections.sort(arrayClone);
		if (arrayClone.size() < NBOFMAC) return arrayClone;
		for (int i = 0; i < NBOFMAC; i++) 
			relevantScan.add(arrayClone.get(i));
		return relevantScan;
	}

}
