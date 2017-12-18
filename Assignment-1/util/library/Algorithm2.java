package library;

import java.util.ArrayList;
import java.util.Collections;

import algorithms.Mac;
import algorithms.MacLocation;
import algorithms.MacLocationAlgo2;
import algorithms.WeigthAverage;
import read.SampleScan;
import read.Wifi;

/**
 * This class calculate all the data by the algorithm 2 formulas.
 * The {@link SampleScan} input and data need to be of the same size, and the noSignal need to be initialize.
 * @author Orel and Samuel.
 */
public class Algorithm2 {

	private static final int numberOfSampleScan = 4;
	private static final int numberOfWifi = 4;
	private static final int power = 2;
	private static final int norm = 10000;
	private static final double sigDif = 0.4;
	private static final int minDif = 3;
	private static final int noSignal = -120;
	private static final int difNoSignal = 100;

	//Revelant Number

	/**
	 * This 
	 * @param input
	 * @param arrayData
	 * @return
	 */
	public static ArrayList<WeigthAverage> setArrayRelevantNumber(SampleScan input, ArrayList<WeigthAverage> arrayData) {
		for (WeigthAverage data : arrayData) 
			inputRelevantNumber(input, data);
		Collections.sort(arrayData);
		ArrayList<WeigthAverage> arrayTemp = new ArrayList<WeigthAverage>();
		for (int i = 0; i < numberOfSampleScan; i++) {
			arrayTemp.add(arrayData.get(i));
		}
		return arrayTemp;
	}

	private static void inputRelevantNumber(SampleScan input, WeigthAverage data) {
		double number = 1;
		for (Wifi inputWifi : input.getArrayStrongerWifiConstantNumber(numberOfWifi)) {
			Wifi dataWifi = data.getSampleScan().containsSameMac(inputWifi.getMac());
			number *= revelantNumber(inputWifi, dataWifi);
			if (dataWifi == null) 
				dataWifi = new Wifi("NONE", "NONE", 0, -120);
			data.addWifi(dataWifi);
		}
		if (number == 1) number = 0;
		data.setRelevantNumber(number);
	}

	private static double revelantNumber(Wifi input, Wifi data) {
		if (data == null) 
			return 0.1;
		return 
				((Math.abs(input.getSignal())
						- Math.abs(data.getSignal() - input.getSignal()))
						/ Math.abs(input.getSignal()) );		
	}

	// PI

	public static ArrayList<WeigthAverage> setArrayPi(SampleScan input, ArrayList<WeigthAverage> arrayData) {
		for (WeigthAverage data : arrayData) 
			inputPi(input, data);
		Collections.sort(arrayData, WeigthAverage.Comparators.PI);
		return arrayData;
	}

	private static void inputPi(SampleScan input, WeigthAverage data) {
		double pi = 1;
		int count = 0;
		for (Wifi wifi : data.getArrayWifi()) 
			pi *= getWeight(input.getArrayStrongerWifiConstantNumber(numberOfWifi).get(count), wifi);
		data.setPi(pi);
	}

	private static double getWeight(Wifi input, Wifi data) {
		return
				norm / (
						Math.pow(
								getDiff(input, data),
								sigDif
								) *
						Math.pow(
								input.getSignal(),
								power
								)
						);
	}

	private static double getDiff(Wifi input, Wifi data) {
		if(data.getSignal() == noSignal) return difNoSignal;
		else return Math.max(
				minDif,
				Math.abs(
						input.getSignal()
						- data.getSignal()
						)
				);
	}
	
	//Localisation

	public static void setLocation(SampleScan input, ArrayList<WeigthAverage> arrayData) {
		ArrayList<MacLocation> arrayMacLocation = new ArrayList<MacLocation>();
		for (int i = 0; i < 3; i++) {
			arrayMacLocation.add(
					new MacLocationAlgo2(
							arrayData.get(i).getSampleScan().getPointLocation(),
							arrayData.get(i).getPi()
							)
					);
		}
		Mac mac = new Mac(arrayMacLocation);
		input.setPointLocation(mac.getWeightCenter());
	}
	
}
