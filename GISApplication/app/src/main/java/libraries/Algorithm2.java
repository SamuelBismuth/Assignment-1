package libraries;

import java.util.ArrayList;
import java.util.Collections;

import objects.Mac;
import objects.MacInformation;
import objects.MacInformationAlgo2;
import objects.SampleScan;
import objects.WeigthAverage;
import objects.Wifi;

/**
 * This class includes all the static function which calculate all the data by the algorithm 2 formulas.
 * Here all the function are static.
 * The explnation we could give (as students) is the next one :
 * Here all the methods are used to be a mathematical reflexion. There is no object into those functions.
 * For exemple f(x) = 2x + 4 changes the x into 2x + 4, here the methods do the same.
 * That's why we choosed the do a large class which play the role of library for all the formulas of the algorithm 2.
 * The {@link SampleScan} input and data need to be of the same size, and the noSignal need to be initialize.
 * @author Orel and Samuel.
 */
public class Algorithm2 {

	/**
	 * Here all the parameters used by the formulas.
	 * It's our job to change them for arrive to the best answer possible.
	 */
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
	 * This method create a {@link ArrayList} of {@link WeigthAverage} from the input and the data.
	 * This new object {@link WeigthAverage} includes all the data needed for the for the algorithm 2 formulas.
	 * @param input.
	 * @param arrayData.
	 */
	public static void setArrayRelevantNumber(SampleScan input, ArrayList<WeigthAverage> arrayData) {
		for (WeigthAverage data : arrayData) 
			inputRelevantNumber(input, data);
		Collections.sort(arrayData);
		arrayData.subList(numberOfSampleScan, arrayData.size()).clear();
	}

	/**
	 * This method set the revelant number into the object {@link Mac}.
	 * What is the revelant number ?
	 * The revelant number is a number define by the formula : 
	 * ((Math.abs(input.getSignal())
						- Math.abs(data.getSignal() - input.getSignal()))
						/ Math.abs(input.getSignal()) ).
	 * More this number are high, more we can trust the data of the mac.
	 * @param input.
	 * @param data.
	 */
	private static void inputRelevantNumber(SampleScan input, WeigthAverage data) {
		double number = 1;
		boolean flag = false;
		for (Wifi inputWifi : input.getArrayStrongerWifiConstantNumber(numberOfWifi)) {
			Wifi dataWifi = data.getSampleScan().containsSameMac(inputWifi.getMac());
			number *= revelantNumber(inputWifi, dataWifi);
			if (dataWifi == null) 
				dataWifi = new Wifi("NONE", "NONE", 0, -120);
			else flag = true;
				data.addWifi(dataWifi);
		}
		if (number == 1) number = 0;
		if (flag == true)
		data.setRelevantNumber(number);
		else data.setRelevantNumber(0);

	}

	/**
	 * This method calculates the revelant number.
	 * @param input.
	 * @param data.
	 * @return the revelant number.
	 */
	public static double revelantNumber(Wifi input, Wifi data) {
		if (data == null) 
			return 0.1;
		return 
				((Math.abs(input.getSignal())
						- Math.abs(data.getSignal() - input.getSignal()))
						/ Math.abs(input.getSignal()) );		
	}

	// PI

	/**
	 * For an {@link ArrayList} of {@link WeigthAverage} given, this method set the number pi.
	 * @param input.
	 * @param arrayData.
	 * @return the array of {@link WeigthAverage}.
	 */
	public static ArrayList<WeigthAverage> setArrayPi(SampleScan input, ArrayList<WeigthAverage> arrayData) {
		for (WeigthAverage data : arrayData) 
			inputPi(input, data);
		Collections.sort(arrayData, WeigthAverage.Comparators.PI);
		return arrayData;
	}

	/**	 
	 * This method set the pi number into the object {@link WeigthAverage}.
	 * What is the pi number ?
	 * The pi number is a number define by the that follow into the getWeigth function and getDiff function. 
	 * This number remplace the weigth of the algorithm 1. Recall that weigth = 1 / (signal * signal)
	 * @param input
	 * @param data
	 */
	public static void inputPi(SampleScan input, WeigthAverage data) {
		double pi = 1;
		int count = 0;
		for (Wifi wifi : data.getArrayWifi()) {
			pi *= getWeight(input.getArrayStrongerWifiConstantNumber(numberOfWifi).get(count), wifi);
		}
		data.setPi(pi);
	}

	/**
	 * This method calculate the weigth number.
	 * @param input.
	 * @param data.
	 * @return the weigth number.
	 */
	public static double getWeight(Wifi input, Wifi data) {
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

	/**
	 * This method calculate the diff number.
	 * @param input.
	 * @param data.
	 * @return the diff number.
	 */
	public static double getDiff(Wifi input, Wifi data) {
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

	/**
	 * This method is the finality of the algorithm 2 : it's set the location.
	 * To do this, the method create a new object {@link MacInformation}. 
	 * Into this object, all the calculates of the algorithm 1 are made, then, we have the gps coordinates.
	 * @param input
	 * @param arrayData
	 */
	public static void setLocation(SampleScan input, ArrayList<WeigthAverage> arrayData) {
		ArrayList<MacInformation> arrayMacLocation = new ArrayList<MacInformation>();
		for (int i = 0; i < 3; i++) {
			arrayMacLocation.add(
					new MacInformationAlgo2(
							arrayData.get(i).getSampleScan().getPointLocation(),
							arrayData.get(i).getArrayWifi().get(0),
							arrayData.get(i).getPi()
							)
					);
		}
		input.setPointLocation(Algorithm1.getWeightCenter(arrayMacLocation));
	}

	public static void mmeset(ArrayList<WeigthAverage> arrayData) {
		for (WeigthAverage scan : arrayData) {
			scan.setPi(0);
			scan.setRelevantNumber(0);
			scan.getArrayWifi().clear();
		}
	}

}
