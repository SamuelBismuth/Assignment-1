package assignment1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represente a Mac object.
 * @author Orel and Samuel.
 */
public class Mac  {

	private String macName;
	private ArrayList<MacLocation> arrayMacLocationInformation;
	
	/**
	 * Constructor.
	 * @param macName.
	 * @param arrayMacLocationInformation.
	 * TODO : sort the array by the signal.
	 */
	public Mac(String macName, ArrayList<MacLocation> arrayMacLocationInformation) {
		this.macName = macName;
		this.arrayMacLocationInformation = arrayMacLocationInformation;
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
		return arrayMacLocationInformation.size();
	}

	/**
	 * @return arrayMacLocationInformation.
	 */
	public ArrayList<MacLocation> getArrayMacLocationInformation() {
		return arrayMacLocationInformation;
	}
	
	public void sort() {
		Collections.sort(arrayMacLocationInformation);
	}
	
	/**
	 * @return strongerSignal
	 */
	public int getStrongerSignal() {
		return arrayMacLocationInformation.get(0).getSignal();
	}

}
