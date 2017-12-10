package assignment1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represente a Mac object.
 * @author Orel and Samuel.
 */
public class Mac  {

	private String macName;
	private ArrayList<MacLocation> arrayMacLocation;
	
	/**
	 * Constructor.
	 * @param macName.
	 * @param arrayMacLocationInformation.
	 * TODO : sort the array by the signal.
	 */
	public Mac(String macName, ArrayList<MacLocation> arrayMacLocationInformation) {
		this.macName = macName;
		this.arrayMacLocation = arrayMacLocationInformation;
		sort();
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
	 * @return arrayMacLocationInformation.
	 */
	public ArrayList<MacLocation> getArrayMacLocation() {
		return arrayMacLocation;
	}
	
	public void sort() {
		Collections.sort(arrayMacLocation);
	}
	
	/**
	 * @return strongerSignal
	 */
	public int getStrongerSignal() {
		return arrayMacLocation.get(0).getSignal();
	}

}
