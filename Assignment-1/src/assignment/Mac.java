package assignment;

import java.util.ArrayList;
import java.util.Collections;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class represents all samples of a single wifi router (with a single mac address)
 * @author Orel and Samuel.
 */
public class Mac  {

	// the mac address of the router
	private String macName;
	
	// all the samples of this router
	private ArrayList<MacLocation> arrayMacLocation;

	// boolean = true if the mac is already used.
	private boolean used;
	
	/**
	 * Constructor.
	 * @param macName.
	 * @param arrayMacLocationInformation.
	 */
	protected Mac(String macName, ArrayList<MacLocation> arrayMacLocationInformation) {
		this.macName = macName;
		this.arrayMacLocation = arrayMacLocationInformation;
		this.used = false;
		sort();
	}

	/**
	 * @return macName.
	 */
	protected String getMacName() {
		return macName;
	}

	/**
	 * @return numberOfMac.
	 */
	protected int getNumberOfMac() {
		return arrayMacLocation.size();
	}

	/**
	 * @return arrayMacLocation.
	 */
	protected ArrayList<MacLocation> getArrayMacLocation() {
		return arrayMacLocation;
	}

	/**
	 * This method calculates the sum between all pointLocation of the weights.
	 * @return SumWeightPointLocation.
	 */
	protected EarthCoordinate getSumWeightPointLocation() {
		double sumWeigthLatitude = 0;
		double sumWeigthLongitude = 0;
		double sumWeigthAltitude = 0;
		for(MacLocation macLocation : arrayMacLocation) {
			sumWeigthLatitude += macLocation.getWeightPointLocation().getLatitude();
			sumWeigthLongitude += macLocation.getWeightPointLocation().getLongitude();
			sumWeigthAltitude += macLocation.getWeightPointLocation().getAltitude();
		}
		return new EarthCoordinate(
				sumWeigthLatitude,
				sumWeigthLongitude,
				sumWeigthAltitude
				);
	}

	/**
	 * This method calculates the sum between all the signal of the weights.
	 * @return sumWeigthSignal.
	 */
	protected double getSumWeightSignal() {
		double sumWeigthSignal = 0;
		for(MacLocation macLocation : arrayMacLocation) sumWeigthSignal += macLocation.getWeigthSignal();
		return sumWeigthSignal;
	}

	/**
	 * This method return the weight center.
	 * @return weightCenter.
	 */
	protected EarthCoordinate getWeightCenter() {
		return new EarthCoordinate(
				getSumWeightPointLocation().getLatitude() / getSumWeightSignal(),
				getSumWeightPointLocation().getLongitude() / getSumWeightSignal(),
				getSumWeightPointLocation().getAltitude() / getSumWeightSignal()
				);
	}

	/**
	 * @return strongerSignal.
	 */
	protected int getStrongerSignal() {
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
	 * This method sort 
	 */
	protected void sort() {
		Collections.sort(arrayMacLocation);
	}

}
