package algorithms;

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
	private ArrayList<MacLocation> arrayMacLocation; // all the samples of this router
	private GregorianCalendar date;
	private boolean used; // boolean = true if the mac is already used.
	private LineAlgo1 lineAlgo1;

	/**
	 * Constructor.
	 * @param macName.
	 * @param arrayMacLocationInformation.
	 * @param date.
	 */
	public Mac(String macName, ArrayList<MacLocation> arrayMacLocationInformation, GregorianCalendar date) {
		this.macName = macName;
		sort(arrayMacLocationInformation);
		this.arrayMacLocation = defineArrayListWithFinalNumber(arrayMacLocationInformation);
		this.date = date;
		this.used = false;
	}

	/**
	 * Constructor.
	 * @param arrayMacLocationInformation.
	 */
	public Mac(ArrayList<MacLocation> arrayMacLocationInformation) {
		sort(arrayMacLocationInformation);
		this.arrayMacLocation = defineArrayListWithFinalNumber(arrayMacLocationInformation);
		this.used = false;
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
		return arrayMacLocation.size();
	}

	/**
	 * @return arrayMacLocation.
	 */
	public ArrayList<MacLocation> getArrayMacLocation() {
		return arrayMacLocation;
	}

	/**
	 * @return strongerSignal.
	 */
	protected double getStrongerSignal() {
		return arrayMacLocation.get(0).getSignal();
	}

	/**
	 * @return used
	 */
	protected boolean getUsed() {
		return used;
	}

	/**
	 * @param bool
	 */
	protected void setUsed(boolean bool) {
		this.used = bool;
	}
	
	/**
	 * @return arrayLineAlgo1.
	 */
	public LineAlgo1 getLineAlgo1() {
		return lineAlgo1;
	}

	/**
	 * @param arrayLineAlgo1.
	 */
	public void setLineAlgo1(LineAlgo1 lineAlgo1) {
		this.lineAlgo1 = lineAlgo1;
	}
	
	/**
	 * @return weight center.
	 */
	public EarthCoordinate getWeightCenter() {
		return Algorithm1.getWeightCenter(arrayMacLocation);
	}

	//Comparator.
	
	/**
	 * This method sort 
	 */
	public void sort(ArrayList<MacLocation> array) {
		Collections.sort(array, MacLocation.Comparators.SIGNAL);
	}

	//private method.

	/**
	 * This method return an {@link ArrayList} of {@link MacLocation} with the number of sample define in numberOfSampleMac.
	 * @param arrayMacLocationInformation
	 * @return
	 */
	private ArrayList<MacLocation> defineArrayListWithFinalNumber(ArrayList<MacLocation> arrayMacLocationInformation) {
		if (arrayMacLocationInformation.size() < numberOfSampleMac) 
			return arrayMacLocationInformation;
		else {
			ArrayList<MacLocation> array = new ArrayList<MacLocation>();
			for (int i = 0; i < numberOfSampleMac; i++) 
				array.add(arrayMacLocationInformation.get(i));
			return array;
		}
	}
}
