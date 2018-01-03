package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import libraries.Algorithm1;

/**
 * This class represents all samples of a single wifi router (with a single mac address).
 * @author Orel and Samuel.
 */
public class Mac  {

	private static final int numberOfSampleMac = 4;

	private String macName; // the mac address of the router
	private ArrayList<MacInformation> arrayMacInformation; // all the samples of this router
	private GregorianCalendar date;

	/**
	 * Constructor.
	 * @param macName.
	 * @param arrayMacInformation.
	 * @param date.
	 */
	public Mac(String macName, ArrayList<MacInformation> arrayMacInformation, GregorianCalendar date) {
		this.macName = macName;
		sort(arrayMacInformation);
		this.arrayMacInformation = defineArrayListWithFinalNumber(arrayMacInformation);
		this.date = date;
	}

	/**
	 * Constructor.
	 * @param macName
	 * @param arrayMacInformation
	 */
	public Mac(String macName, ArrayList<MacInformation> arrayMacInformation) {
		this.macName = macName;
		sort(arrayMacInformation);
		this.arrayMacInformation = arrayMacInformation;
	}
	/**
	 * Constructor.
	 * @param arrayMacLocationInformation.
	 */
	public Mac(ArrayList<MacInformation> arrayMacInformation) {
		sort(arrayMacInformation);
		this.arrayMacInformation = defineArrayListWithFinalNumber(arrayMacInformation);
	}

	//Getters and setters.
	
	/**
	 * @return date.
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * @return macName.
	 */
	public String getMacName() {
		return macName;
	}

	/**
	 * @return numberOfMac.
	 */
	public int getNumberOfMac() {
		return arrayMacInformation.size();
	}

	/**
	 * @return arrayMacLocation.
	 */
	public ArrayList<MacInformation> getArrayMacLocation() {
		return arrayMacInformation;
	}

	/**
	 * @return strongerSignal.
	 */
	protected double getStrongerSignal() {
		return arrayMacInformation.get(0).getSignal();
	}
	
	/**
	 * @return weight center.
	 */
	public EarthCoordinate getWeightCenter() {
		return Algorithm1.getWeightCenter(arrayMacInformation);
	}

	//Comparator.
	
	/**
	 * This method sort 
	 */
	public void sort(ArrayList<MacInformation> array) {
		Collections.sort(array, MacInformation.Comparators.SIGNAL);
	}

	//private method.

	/**
	 * This method return an {@link ArrayList} of {@link MacInformation} with the number of sample define in numberOfSampleMac.
	 * @param arrayMacLocationInformation
	 * @return
	 */
	public ArrayList<MacInformation> defineArrayListWithFinalNumber(ArrayList<MacInformation> arrayMacLocationInformation) {
		if (arrayMacLocationInformation.size() < numberOfSampleMac) 
			return arrayMacLocationInformation;
		else {
			ArrayList<MacInformation> array = new ArrayList<MacInformation>();
			for (int i = 0; i < numberOfSampleMac; i++) 
				array.add(arrayMacLocationInformation.get(i));
			return array;
		}
	}
}
